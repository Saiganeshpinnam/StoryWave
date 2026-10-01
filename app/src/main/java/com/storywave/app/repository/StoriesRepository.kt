package com.storywave.app.repository

import com.storywave.app.data.local.StoryDao
import com.storywave.app.data.local.MockStoriesData
import com.storywave.app.data.remote.RetrofitService
import com.storywave.app.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import android.util.Log
import retrofit2.Response
import okhttp3.ResponseBody

class StoriesRepository(
    private val storyDao: StoryDao,
    private val retrofitService: RetrofitService,
    private val localRetrofitService: RetrofitService? = null
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

    // AUTH LOGIC (Spring Boot / Remote API)
    suspend fun login(request: LoginRequest): Response<ResponseBody> {
        return if (localRetrofitService != null) {
            try {
                val localResp = localRetrofitService.login(request)
                if (localResp.isSuccessful) {
                    localResp
                } else {
                    try {
                        val remoteResp = retrofitService.login(request)
                        if (remoteResp.isSuccessful) remoteResp else localResp
                    } catch (e: Exception) {
                        localResp
                    }
                }
            } catch (e: Exception) {
                retrofitService.login(request)
            }
        } else {
            retrofitService.login(request)
        }
    }

    suspend fun loginFallback(request: LoginRequest): Response<ResponseBody> {
        return if (localRetrofitService != null) {
            try {
                val localResp = localRetrofitService.loginFallback(request)
                if (localResp.isSuccessful) {
                    localResp
                } else {
                    try {
                        val remoteResp = retrofitService.loginFallback(request)
                        if (remoteResp.isSuccessful) remoteResp else localResp
                    } catch (e: Exception) {
                        localResp
                    }
                }
            } catch (e: Exception) {
                retrofitService.loginFallback(request)
            }
        } else {
            retrofitService.loginFallback(request)
        }
    }

    suspend fun register(request: RegisterRequest): Response<ResponseBody> {
        return if (localRetrofitService != null) {
            try {
                val localResp = localRetrofitService.register(request)
                if (localResp.isSuccessful) {
                    try { retrofitService.register(request) } catch (_: Exception) {}
                    localResp
                } else {
                    try {
                        val remoteResp = retrofitService.register(request)
                        if (remoteResp.isSuccessful) remoteResp else localResp
                    } catch (e: Exception) {
                        localResp
                    }
                }
            } catch (e: Exception) {
                retrofitService.register(request)
            }
        } else {
            retrofitService.register(request)
        }
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
        try {
            storyDao.toggleBookmark(id)
            retrofitService.toggleBookmark(id)
        } catch (e: Exception) {
            Log.e("StoriesRepo", "Bookmark sync error", e)
        }
    }

    suspend fun updateProgressInRoom(id: Int, progress: Int) {
        try {
            storyDao.updateProgress(id, progress)
            retrofitService.updateProgress(id, progress)
        } catch (e: Exception) {
            Log.e("StoriesRepo", "Progress sync error", e)
        }
    }

    suspend fun getUserStats(): UserStats {
        return try {
            retrofitService.getUserStats()
        } catch (e: Exception) {
            Log.e("StoriesRepo", "Fetch stats error", e)
            UserStats()
        }
    }

    suspend fun resetDatabaseInRoom() {
        try {
            storyDao.insertStories(MockStoriesData.mockStories)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
