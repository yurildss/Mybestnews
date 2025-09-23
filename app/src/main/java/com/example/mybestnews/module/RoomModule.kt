package com.example.mybestnews.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybestnews.repository.NewsDao
import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.OfflineNewsDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): OfflineNewsDatabase {
        return Room.databaseBuilder(
            context,
            OfflineNewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideNewsDao(database: OfflineNewsDatabase): NewsDao {
        return database.newsDao()
    }
}
