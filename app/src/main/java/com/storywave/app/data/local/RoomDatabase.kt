package com.storywave.app.data.local

import androidx.room.*
import com.storywave.app.model.Story
import com.storywave.app.model.User
import kotlinx.coroutines.flow.Flow

@Database(entities = [Story::class, User::class], version = 2, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
    abstract fun userDao(): UserDao
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

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUserByEmail(email: String)
}
