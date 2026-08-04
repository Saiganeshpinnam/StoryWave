package com.storywave.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class Story(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val content: String,
    val coverUrl: String,
    val readingTime: Int,
    val difficulty: String,
    val categoryId: String,
    val isFeatured: Boolean,
    val isPopular: Boolean,
    var progress: Int = 0,
    var isBookmarked: Boolean = false
)

data class Category(
    val id: String,
    val name: String,
    val description: String,
    val iconName: String,
    val color: String
)

data class UserStats(
    val storiesRead: Int = 0,
    val dayStreak: Int = 0,
    val minutesSpent: Double = 0.0,
    val favoriteCount: Int = 0
)
