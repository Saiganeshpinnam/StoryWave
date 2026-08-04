package com.storywave.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storywave.app.model.Story
import com.storywave.app.model.Category
import com.storywave.app.model.UserStats
import com.storywave.app.model.User
import com.storywave.app.repository.StoriesRepository
import com.storywave.app.data.remote.NetworkObserver
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StoriesViewModel(
    private val repository: StoriesRepository,
    private val networkObserver: NetworkObserver
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _userStats = MutableStateFlow(UserStats(storiesRead = 2, dayStreak = 4, minutesSpent = 184.0, favoriteCount = 1))
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _currentUserEmail = MutableStateFlow<String?>(null)
    val currentUserEmail: StateFlow<String?> = _currentUserEmail.asStateFlow()

    init {
        observeConnectivity()
        loadContent()
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            networkObserver.observe.collect { online ->
                _isOnline.value = online
                if (online) {
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
            // Simulated minimal delay for UI feedback
            kotlinx.coroutines.delay(500)
            
            val user = repository.getUserByEmail(email)
            _isLoading.value = false
            
            if (user == null) {
                onResult(false, "User not found. Please register first.")
            } else if (user.password != password) {
                onResult(false, "Incorrect password. Please try again.")
            } else {
                _currentUserEmail.value = email
                onResult(true, null)
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
            val existing = repository.getUserByEmail(email)
            if (existing != null) {
                _isLoading.value = false
                onResult(false, "This email is already registered.")
                return@launch
            }

            val newUser = User(email, username, password)
            val success = repository.registerUser(newUser)
            _isLoading.value = false
            
            if (success) {
                onResult(true, null)
            } else {
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
        // Clear any user-specific data if stored in StateFlows
        _currentUserEmail.value = null
    }

    fun deleteAccount(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val email = _currentUserEmail.value
            if (email != null) {
                repository.deleteUser(email)
                logout()
                onComplete(true)
            } else {
                onComplete(false)
            }
        }
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
