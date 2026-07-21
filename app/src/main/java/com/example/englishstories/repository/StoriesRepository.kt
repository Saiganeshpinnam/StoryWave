package com.example.englishstories.repository

import com.example.englishstories.data.local.StoryDao
import com.example.englishstories.data.local.MockStoriesData
import com.example.englishstories.data.remote.RetrofitService
import com.example.englishstories.model.Story
import com.example.englishstories.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class StoriesRepository(
    private val storyDao: StoryDao,
    private val retrofitService: RetrofitService
) {
    // ROOM DATABASE DAO ACCESS (Local cache Flow)
    fun getCachedStories(): Flow<List<Story>> = storyDao.getAllStoriesFlow()

    // Seeds the database with the default mock stories if empty
    suspend fun seedDatabaseIfEmpty() {
        try {
            val current = storyDao.getAllStoriesFlow().firstOrNull() ?: emptyList()
            if (current.isEmpty()) {
                storyDao.insertStories(MockStoriesData.mockStories)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback seed in case flow collection has issues
            try {
                storyDao.insertStories(MockStoriesData.mockStories)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    // RETROFIT API CALLS (PostgreSQL Sync)
    suspend fun syncStoriesWithBackend() {
        try {
            val remoteStories = retrofitService.getStories()
            if (remoteStories.isNotEmpty()) {
                storyDao.insertStories(remoteStories) // Save to local SQLite cache
            } else {
                seedDatabaseIfEmpty()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            seedDatabaseIfEmpty() // Fallback to local seed on network error
        }
    }

    suspend fun getRemoteCategories(): List<Category> {
        return try {
            val remoteCats = retrofitService.getCategories()
            if (remoteCats.isNotEmpty()) remoteCats else MockStoriesData.mockCategories
        } catch (e: Exception) {
            MockStoriesData.mockCategories // Local offline-first fallback
        }
    }

    suspend fun searchStories(query: String): List<Story> {
        return if (query.isEmpty()) {
            emptyList()
        } else {
            try {
                val remoteResults = retrofitService.searchStories(query)
                if (remoteResults.isNotEmpty()) {
                    remoteResults
                } else {
                    searchStoriesLocally(query)
                }
            } catch (e: Exception) {
                searchStoriesLocally(query) // Fallback to local search
            }
        }
    }

    private suspend fun searchStoriesLocally(query: String): List<Story> {
        val cleaned = query.lowercase().trim()
        val allStories = storyDao.getAllStoriesFlow().firstOrNull() ?: MockStoriesData.mockStories
        return allStories.filter { story ->
            story.title.lowercase().contains(cleaned) ||
            story.description.lowercase().contains(cleaned) ||
            story.categoryId.lowercase().contains(cleaned)
        }
    }

    suspend fun toggleBookmarkInRoom(id: Int) {
        storyDao.toggleBookmark(id)
    }

    suspend fun updateProgressInRoom(id: Int, progress: Int) {
        storyDao.updateProgress(id, progress)
    }

    // Clear and restore original seeded database content
    suspend fun resetDatabaseInRoom() {
        try {
            storyDao.insertStories(MockStoriesData.mockStories)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

