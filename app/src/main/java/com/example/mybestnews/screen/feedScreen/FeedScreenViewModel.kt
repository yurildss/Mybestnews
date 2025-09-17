package com.example.mybestnews.screen.feedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.model.News
import com.example.mybestnews.repository.UserPreferencesRepository
import com.example.mybestnews.services.NewsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject
constructor(
    private val repository: UserPreferencesRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            val userPreferences = repository.getUserPreferences()
            userPreferences.collect {
                val news = NewsAPI.retrofitService.getNewsByCategoryLanguageCountry(it.favoriteTagsList.first())
                _uiState.value = FeedScreenUIState(news[2].articles.toMutableList())
            }

        }
    }

    val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()
}

data class FeedScreenUIState(
    val news: MutableList<News> = mutableListOf()
)