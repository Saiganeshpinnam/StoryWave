package com.example.englishstories.data.local

import androidx.room.*
import com.example.englishstories.model.Story
import kotlinx.coroutines.flow.Flow

@Database(entities = [Story::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories ORDER BY id ASC")
    fun getAllStoriesFlow(): Flow<List<Story>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<Story>)

    @Query("UPDATE stories SET isBookmarked = NOT isBookmarked WHERE id = :storyId")
    suspend fun toggleBookmark(storyId: Int)

    @Query("UPDATE stories SET progress = :progress WHERE id = :storyId")
    suspend fun updateProgress(storyId: Int, progress: Int)

    @Query("SELECT * FROM stories WHERE isBookmarked = 1")
    fun getBookmarkedStoriesFlow(): Flow<List<Story>>
}
