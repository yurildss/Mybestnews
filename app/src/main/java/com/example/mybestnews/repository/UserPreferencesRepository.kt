package com.example.mybestnews.repository

import androidx.datastore.core.DataStore
import com.example.application.proto.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun saveFavoriteTags(tags: List<String>) {
        dataStore.updateData { current ->
            current.toBuilder()
                .clearFavoriteTags()
                .addAllFavoriteTags(tags)
                .build()
        }
    }

    suspend fun saveFavoriteLanguage(language: List<String>) {
        dataStore.updateData { current ->
            current.toBuilder()
                .clearFavoriteLanguage()
                .addAllFavoriteLanguage(language)
                .build()
        }
    }

    suspend fun saveFavoriteCountry(country: List<String>) {
        dataStore.updateData { current ->
            current.toBuilder()
                .clearFavoriteCountry()
                .addAllFavoriteCountry(country)
                .build()
        }
    }

    suspend fun updateNewUserStatus(status: Boolean){
        dataStore.updateData { current ->
            current.toBuilder()
                .setNewUser(status)
                .build()
        }
    }
}
