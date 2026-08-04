package com.storywave.app.repository

import com.storywave.app.data.local.StoryDao
import com.storywave.app.data.local.UserDao
import com.storywave.app.data.local.MockStoriesData
import com.storywave.app.data.remote.RetrofitService
import com.storywave.app.model.Story
import com.storywave.app.model.Category
import com.storywave.app.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import android.util.Log

class StoriesRepository(
    private val storyDao: StoryDao,
    private val userDao: UserDao,
    private val retrofitService: RetrofitService
) {
    // ROOM DATABASE DAO ACCESS (Local cache Flow)
    fun getCachedStories(): Flow<List<Story>> = storyDao.getAllStoriesFlow()

    suspend fun seedDatabaseIfEmpty() {
        try {
            val current = storyDao.getAllStoriesFlow().firstOrNull() ?: emptyList()
            if (current.isEmpty()) {
                storyDao.insertStories(MockStoriesData.mockStories)
            }
        } catch (e: Exception) {
            Log.e("StoriesRepo", "Seed error", e)
        }
    }

    // AUTH LOGIC
    suspend fun registerUser(user: User): Boolean {
        return try {
            userDao.insertUser(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun deleteUser(email: String) {
        userDao.deleteUserByEmail(email)
    }

    // RETROFIT API CALLS (PostgreSQL Sync)
    suspend fun syncStoriesWithBackend() {
        try {
            val remoteStories = retrofitService.getStories()
            if (remoteStories.isNotEmpty()) {
                storyDao.insertStories(remoteStories)
            }
        } catch (e: Exception) {
            Log.e("StoriesRepo", "Sync error", e)
        }
    }

    suspend fun getRemoteCategories(): List<Category> {
        return try {
            val remoteCats = retrofitService.getCategories()
            if (remoteCats.isNotEmpty()) remoteCats else MockStoriesData.mockCategories
        } catch (e: Exception) {
            MockStoriesData.mockCategories
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
                searchStoriesLocally(query)
            }
        }
    }

    private suspend fun searchStoriesLocally(query: String): List<Story> {
        val cleaned = query.lowercase().trim()
        val allStories = storyDao.getAllStoriesFlow().firstOrNull() ?: emptyList()
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

    suspend fun resetDatabaseInRoom() {
        try {
            storyDao.insertStories(MockStoriesData.mockStories)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
