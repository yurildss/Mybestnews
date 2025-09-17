package com.example.mybestnews.screen.feedScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.model.ArticlesRequest
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
                val news = NewsAPI.retrofitService.getNewsByCategoryLanguageCountry(
                    ArticlesRequest(
                    keywords = mapOf("keyword" to "technology"),
                    lang = "eng",
                    page = 1,
                    pageSize = 20))
                if(news.isSuccessful){

                }
            }

        }
    }

    val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()
}

data class FeedScreenUIState(
    val news: MutableList<News> = mutableListOf()
)