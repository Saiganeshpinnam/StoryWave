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
                var response: Response<ResponseBody>? = null
                var rawBody: String? = null
                var retryCount = 0
                val maxRetries = 5

                // Aggressive Auto-Retry Loop with Exponential Backoff
                while (retryCount <= maxRetries) {
                    response = repository.login(LoginRequest(email, password))
                    
                    if (response.code() == 404) {
                        Log.d("StoriesVM", "Login 404, trying fallback endpoint")
                        response = repository.loginFallback(LoginRequest(email, password))
                    }

                    rawBody = response.body()?.string() ?: response.errorBody()?.string()
                    
                    val isHtml = rawBody?.trim()?.let { 
                        it.contains("<html", ignoreCase = true) || it.contains("<!doctype html", ignoreCase = true) 
                    } ?: false

                    if (isHtml) {
                        Log.d("StoriesVM", "Security check (HTML) detected (Attempt ${retryCount + 1}).")
                        retryCount++
                        if (retryCount <= maxRetries) {
                            val backoffDelay = 500L * (1 shl (retryCount - 1)) // 500ms, 1000ms, 2000ms...
                            Log.d("StoriesVM", "Retrying in ${backoffDelay}ms...")
                            delay(backoffDelay)
                            continue
                        }
                    }
                    break // Exit loop if not HTML or reached max retries
                }

                _isLoading.value = false
                
                if (response != null && response.isSuccessful && rawBody != null) {
                    try {
                        val authData = Gson().fromJson(rawBody, AuthResponse::class.java)
                        tokenManager.saveAuthData(authData.token)
                        _isAuthenticated.value = true
                        
                        // Sync remote stats after successful verification
                        viewModelScope.launch {
                            val remoteStats = repository.getUserStats()
                            _userStats.value = remoteStats
                        }
                        
                        onResult(true, null)
                    } catch (e: Exception) {
                        Log.e("StoriesVM", "Login parsing error. Raw body: $rawBody")
                        val trimmed = rawBody.trim()
                        if (trimmed.contains("<html", ignoreCase = true) || trimmed.contains("<!doctype html", ignoreCase = true)) {
                            onResult(false, "Security Check: The server is verifying your connection. Please wait a moment and click Sign In again.")
                        } else {
                            onResult(false, "Server message: $rawBody")
                        }
                    }
                } else if (response != null) {
                    Log.e("StoriesVM", "Login failed code ${response.code()}. Raw body: $rawBody")
                    val errorMsg = when (response.code()) {
                        401 -> "wrong password"
                        404 -> "user not registered"
                        else -> "Login failed. Please try again."
                    }
                    onResult(false, errorMsg)
                } else {
                    onResult(false, "Network error. Please check your connection.")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("StoriesVM", "Login network exception", e)
                val errorMsg = when (e) {
                    is java.net.UnknownHostException -> "No internet connection"
                    is java.net.SocketTimeoutException -> "Connection timed out"
                    else -> "Network error: ${e.message}"
                }
                onResult(false, errorMsg)
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
                var response: Response<ResponseBody>? = null
                var rawBody: String? = null
                var retryCount = 0
                val maxRetries = 2

                while (retryCount <= maxRetries) {
                    response = repository.register(RegisterRequest(username, email, password))
                    rawBody = response.body()?.string() ?: response.errorBody()?.string()

                    val isHtml = rawBody?.trim()?.let { 
                        it.contains("<html", ignoreCase = true) || it.contains("<!doctype html", ignoreCase = true) 
                    } ?: false

                    if (isHtml) {
                        Log.d("StoriesVM", "Register security check (HTML) detected (Attempt ${retryCount + 1}). Retrying in 500ms...")
                        retryCount++
                        if (retryCount <= maxRetries) {
                            delay(500)
                            continue
                        }
                    }
                    break
                }

                _isLoading.value = false
                
                if (response != null && response.isSuccessful && rawBody != null) {
                    // Registration success doesn't always return a JSON we need to parse
                    // as we removed the auto-login logic.
                    onResult(true, null)
                } else if (response != null) {
                    Log.e("StoriesVM", "Register failed code ${response.code()}. Raw body: $rawBody")
                    val errorMsg = if (response.code() == 409) "Email already registered" 
                                   else "Registration failed (${response.code()})"
                    onResult(false, errorMsg)
                } else {
                    onResult(false, "Network error during registration.")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("StoriesVM", "Register network exception", e)
                val errorMsg = when (e) {
                    is java.net.UnknownHostException -> "No internet connection"
                    is java.net.SocketTimeoutException -> "Connection timed out"
                    else -> "Network error: ${e.message}"
                }
                onResult(false, errorMsg)
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
