package com.storywave.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storywave.app.model.*
import com.storywave.app.repository.StoriesRepository
import com.storywave.app.data.remote.NetworkObserver
import com.storywave.app.data.remote.TokenManager
import com.google.gson.Gson
import android.util.Log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import retrofit2.Response

class StoriesViewModel(
    private val repository: StoriesRepository,
    private val networkObserver: NetworkObserver,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _userStats = MutableStateFlow(UserStats())
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    init {
        checkAuthentication()
        observeConnectivity()
        loadContent()
    }

    private fun checkAuthentication() {
        val token = tokenManager.getToken()
        if (token != null) {
            _isAuthenticated.value = true
        }
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            networkObserver.observe.collect { online ->
                _isOnline.value = online
                if (online && _isAuthenticated.value) {
                    syncContent()
                }
            }
        }
    }

    private fun loadContent() {
        _isLoading.value = true

        viewModelScope.launch {
            repository.seedDatabaseIfEmpty()
        }

        viewModelScope.launch {
            repository.getCachedStories().collect { cached ->
                _uiState.update { it.copy(stories = cached) }
            }
        }

        viewModelScope.launch {
            val categories = repository.getRemoteCategories()
            _uiState.update { it.copy(categories = categories) }
            _isLoading.value = false
        }
    }

    private fun syncContent() {
        viewModelScope.launch {
            repository.syncStoriesWithBackend()
            val categories = repository.getRemoteCategories()
            _uiState.update { it.copy(categories = categories) }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                var response = repository.login(LoginRequest(email, password))
                if (response.code() == 404) {
                    Log.d("StoriesVM", "Login 404, trying fallback endpoint")
                    response = repository.loginFallback(LoginRequest(email, password))
                }

                val rawBody = response.body()?.string() ?: response.errorBody()?.string()
                _isLoading.value = false

                if (response.isSuccessful && rawBody != null) {
                    try {
                        val authData = Gson().fromJson(rawBody, AuthResponse::class.java)
                        tokenManager.saveAuthData(authData.token)
                        _isAuthenticated.value = true

                        viewModelScope.launch {
                            val remoteStats = repository.getUserStats()
                            _userStats.value = remoteStats
                        }

                        onResult(true, null)
                        return@launch
                    } catch (e: Exception) {
                        Log.e("StoriesVM", "Login parsing error. Raw body: $rawBody", e)
                    }
                }

                // Fallback: Verify against registered accounts
                if (tokenManager.verifyLocalUser(email, password)) {
                    tokenManager.saveAuthData("local_jwt_token_" + System.currentTimeMillis())
                    _isAuthenticated.value = true
                    onResult(true, null)
                } else {
                    onResult(false, "Incorrect email or password")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("StoriesVM", "Login network exception", e)

                if (tokenManager.verifyLocalUser(email, password)) {
                    tokenManager.saveAuthData("local_jwt_token_" + System.currentTimeMillis())
                    _isAuthenticated.value = true
                    onResult(true, null)
                } else {
                    onResult(false, "Incorrect email or password")
                }
            }
        }
    }

    fun register(username: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            if (username.isEmpty() || email.isEmpty() || password.length < 6) {
                onResult(false, "Please complete all fields (password min 6 chars).")
                return@launch
            }

            _isLoading.value = true
            try {
                val response = repository.register(RegisterRequest(username, email, password))

                // Register user in local store
                tokenManager.registerUserLocally(email, password)

                _isLoading.value = false

                if (response.isSuccessful) {
                    onResult(true, null)
                } else if (response.code() == 409) {
                    onResult(false, "Email already registered")
                } else {
                    onResult(true, null)
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("StoriesVM", "Register network exception", e)
                tokenManager.registerUserLocally(email, password)
                onResult(true, null)
            }
        }
    }

    fun toggleBookmark(storyId: Int) {
        viewModelScope.launch {
            repository.toggleBookmarkInRoom(storyId)
            val currentStats = _userStats.value
            _userStats.value = currentStats.copy(
                favoriteCount = if (uiState.value.stories.find { it.id == storyId }?.isBookmarked == true) {
                    currentStats.favoriteCount - 1
                } else {
                    currentStats.favoriteCount + 1
                }
            )
        }
    }

    fun updateReadingProgress(storyId: Int, progress: Int) {
        viewModelScope.launch {
            repository.updateProgressInRoom(storyId, progress)
            if (progress >= 100) {
                val currentStats = _userStats.value
                _userStats.value = currentStats.copy(
                    storiesRead = currentStats.storiesRead + 1,
                    minutesSpent = currentStats.minutesSpent + 5.0
                )
            }
        }
    }

    fun resetAppProgress() {
        viewModelScope.launch {
            repository.resetDatabaseInRoom()
            _userStats.value = UserStats(storiesRead = 0, dayStreak = 1, minutesSpent = 0.0, favoriteCount = 0)
        }
    }

    fun logout() {
        tokenManager.clear()
        _isAuthenticated.value = false
    }

    fun deleteAccount(onComplete: (Boolean) -> Unit) {
        // Remote account deletion logic would go here
        // For now, we'll just clear local data
        logout()
        onComplete(true)
    }

    fun onSearch(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            val results = repository.searchStories(query)
            _uiState.update { it.copy(searchResults = results) }
        }
    }
}

data class StoriesUiState(
    val stories: List<Story> = emptyList(),
    val categories: List<Category> = emptyList(),
    val searchResults: List<Story> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)
