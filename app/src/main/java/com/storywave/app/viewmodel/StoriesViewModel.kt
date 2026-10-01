package com.storywave.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storywave.app.model.*
import com.storywave.app.repository.StoriesRepository
import com.storywave.app.data.remote.NetworkObserver
import com.storywave.app.data.remote.TokenManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import android.util.Log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                networkObserver.observe.collect { online ->
                    _isOnline.value = online
                    if (online && _isAuthenticated.value) {
                        syncContent()
                    }
                }
            } catch (e: Throwable) {
                Log.e("StoriesVM", "Connectivity observation error", e)
            }
        }
    }

    private fun loadContent() {
        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.seedDatabaseIfEmpty()
            } catch (e: Throwable) {
                Log.e("StoriesVM", "Seed DB error", e)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getCachedStories().collect { cached ->
                    _uiState.update { it.copy(stories = cached) }
                }
            } catch (e: Throwable) {
                Log.e("StoriesVM", "Collect stories error", e)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categories = repository.getRemoteCategories()
                _uiState.update { it.copy(categories = categories) }
            } catch (e: Throwable) {
                Log.e("StoriesVM", "Get categories error", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun syncContent() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.syncStoriesWithBackend()
                val categories = repository.getRemoteCategories()
                _uiState.update { it.copy(categories = categories) }
            } catch (e: Throwable) {
                Log.e("StoriesVM", "Sync error", e)
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val (isSuccess, errorMsg) = withContext(Dispatchers.IO) {
                    try {
                        var response = repository.login(LoginRequest(email, password))
                        if (response.code() == 404) {
                            Log.d("StoriesVM", "Login 404, trying fallback endpoint")
                            response = repository.loginFallback(LoginRequest(email, password))
                        }

                        val rawBody = response.body()?.string() ?: response.errorBody()?.string()

                        if (response.isSuccessful) {
                            val tokenToSave: String = if (!rawBody.isNullOrBlank()) {
                                try {
                                    val authData = Gson().fromJson(rawBody, AuthResponse::class.java)
                                    if (!authData?.token.isNullOrEmpty()) {
                                        authData.token!!
                                    } else {
                                        val jsonObj = Gson().fromJson(rawBody, JsonObject::class.java)
                                        (jsonObj.get("token")?.asString
                                            ?: jsonObj.get("jwt")?.asString
                                            ?: jsonObj.get("accessToken")?.asString) ?: rawBody.trim()
                                    }
                                } catch (_: Throwable) {
                                    rawBody.trim()
                                }
                            } else {
                                "jwt_session_token_" + System.currentTimeMillis()
                            }

                            tokenManager.saveAuthData(tokenToSave)
                            _isAuthenticated.value = true

                            try {
                                val remoteStats = repository.getUserStats()
                                _userStats.value = remoteStats
                            } catch (_: Throwable) {}

                            Pair(true, null)
                        } else {
                            Log.e("StoriesVM", "Login failed code ${response.code()}. Raw body: $rawBody")
                            val msg = when (response.code()) {
                                401, 404 -> "Incorrect email or password"
                                else -> "Login failed (${response.code()}). Please try again."
                            }
                            Pair(false, msg)
                        }
                    } catch (e: Throwable) {
                        Log.e("StoriesVM", "Login network exception", e)
                        val msg = when (e) {
                            is java.net.UnknownHostException -> "No internet connection"
                            is java.net.SocketTimeoutException -> "Connection timed out"
                            else -> "Network error: ${e.message}"
                        }
                        Pair(false, msg)
                    }
                }

                _isLoading.value = false
                onResult(isSuccess, errorMsg)
            } catch (e: Throwable) {
                _isLoading.value = false
                Log.e("StoriesVM", "Login outer exception", e)
                onResult(false, "Login failed. Please try again.")
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
                val (isSuccess, errorMsg) = withContext(Dispatchers.IO) {
                    try {
                        val response = repository.register(RegisterRequest(username, email, password))
                        val rawBody = response.body()?.string() ?: response.errorBody()?.string()

                        if (response.isSuccessful) {
                            Pair(true, null)
                        } else if (response.code() == 409) {
                            Pair(false, "Email already registered")
                        } else {
                            Log.e("StoriesVM", "Register failed code ${response.code()}. Raw body: $rawBody")
                            Pair(false, "Registration failed (${response.code()})")
                        }
                    } catch (e: Throwable) {
                        Log.e("StoriesVM", "Register network exception", e)
                        val msg = when (e) {
                            is java.net.UnknownHostException -> "No internet connection"
                            is java.net.SocketTimeoutException -> "Connection timed out"
                            else -> "Network error: ${e.message}"
                        }
                        Pair(false, msg)
                    }
                }

                _isLoading.value = false
                onResult(isSuccess, errorMsg)
            } catch (e: Throwable) {
                _isLoading.value = false
                Log.e("StoriesVM", "Register outer exception", e)
                onResult(false, "Registration failed. Please try again.")
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
