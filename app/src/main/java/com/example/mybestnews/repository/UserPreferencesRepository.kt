package com.example.mybestnews.repository

import androidx.datastore.core.DataStore
import com.example.application.proto.Settings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Settings>
) {

    suspend fun saveUsername(name: String) {
        dataStore.updateData { current ->
            current.toBuilder()
                .setUsername(name)
                .build()
        }
    }

    fun getUserPreferences() = dataStore.data
}
