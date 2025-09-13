package com.example.mybestnews.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.application.proto.Settings
import com.example.mybestnews.preferences.SettingsSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Settings> =
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            produceFile = { context.dataStoreFile("settings.pb") }
        )
}
