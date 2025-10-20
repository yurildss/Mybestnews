package com.example.mybestnews.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mybestnews.model.OfflineNews
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: OfflineNews)
    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews(): Flow<List<OfflineNews>>
}