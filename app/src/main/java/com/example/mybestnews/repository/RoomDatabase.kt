package com.example.mybestnews.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybestnews.model.OfflineNews

@Database(entities = [OfflineNews::class], version = 1, exportSchema = false)
abstract class OfflineNewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
            companion object {
                @Volatile
                private var INSTANCE: OfflineNewsDatabase? = null
                fun getDatabase(context: Context): OfflineNewsDatabase {
                    return INSTANCE?: synchronized(this){
                        Room.databaseBuilder(context, OfflineNewsDatabase::class.java, "news_database")
                            .build()
                            .also {
                                INSTANCE = it
                            }
                    }
                }
            }

}