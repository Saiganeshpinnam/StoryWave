package com.example.englishstories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishstories.model.Story
import com.example.englishstories.model.Category
import com.example.englishstories.model.UserStats
import com.example.englishstories.repository.StoriesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StoriesViewModel(
    private val repository: StoriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoriesUiState())
    val uiState: StateFlow<StoriesUiState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _userStats = MutableStateFlow(UserStats(storiesRead = 2, dayStreak = 4, minutesSpent = 184.0, favoriteCount = 1))
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    init {
        loadContent()
    }

    private fun loadContent() {
        _isLoading.value = true
        
        viewModelScope.launch {
            // Seed the database with local copy if it's currently empty
            repository.seedDatabaseIfEmpty()
        }

        // Collect cached stories as a Flow in a separate job so it doesn't block sync execution
        viewModelScope.launch {
            repository.getCachedStories().collect { cached ->
                _uiState.update { it.copy(stories = cached) }
            }
        }

        viewModelScope.launch {
            // Sync with Retrofit server in background
            try {
                repository.syncStoriesWithBackend()
                val categories = repository.getRemoteCategories()
                _uiState.update { it.copy(categories = categories) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            // Simulated network authentication delay
            kotlinx.coroutines.delay(1000)
            _isLoading.value = false
            if (email.contains("@") && password.length >= 6) {
                onResult(true, null)
            } else {
                onResult(false, "Invalid email address or password (min 6 characters)")
            }
        }
    }

    fun register(username: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            // Simulated network registration delay
            kotlinx.coroutines.delay(1000)
            _isLoading.value = false
            if (username.isNotEmpty() && email.contains("@") && password.length >= 6) {
                onResult(true, null)
            } else {
                onResult(false, "Please complete all fields with a secure password.")
            }
        }
    }

    fun toggleBookmark(storyId: Int) {
        viewModelScope.launch {
            repository.toggleBookmarkInRoom(storyId)
            // Update local stat representation
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
        // Handle any session cleanup if necessary
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
