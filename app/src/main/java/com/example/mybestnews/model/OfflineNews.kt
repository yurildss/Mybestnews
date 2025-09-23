package com.example.mybestnews.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class OfflineNews(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val url: String,
    val publishedAt: String,
    val source: Source
)