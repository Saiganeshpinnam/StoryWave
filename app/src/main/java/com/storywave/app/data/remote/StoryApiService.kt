package com.storywave.app.data.remote

import com.storywave.app.model.Story
import com.storywave.app.model.Category
import com.storywave.app.model.LoginRequest
import com.storywave.app.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.Response
import okhttp3.ResponseBody
import com.storywave.app.model.UserStats

interface RetrofitService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ResponseBody>

    @POST("login")
    suspend fun loginFallback(@Body request: LoginRequest): Response<ResponseBody>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ResponseBody>

    @GET("stories")
    suspend fun getStories(): List<Story>

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("stories/search")
    suspend fun searchStories(@Query("q") query: String): List<Story>

    @POST("stories/{id}/bookmark")
    suspend fun toggleBookmark(@Path("id") id: Int): Response<ResponseBody>

    @POST("stories/{id}/progress")
    suspend fun updateProgress(@Path("id") id: Int, @Query("progress") progress: Int): Response<ResponseBody>

    @GET("user/stats")
    suspend fun getUserStats(): UserStats
}
