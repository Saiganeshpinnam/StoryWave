package com.example.englishstories.data.remote

import com.example.englishstories.model.Story
import com.example.englishstories.model.Category
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("stories")
    suspend fun getStories(): List<Story>

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("stories/search")
    suspend fun searchStories(@Query("q") query: String): List<Story>
}
