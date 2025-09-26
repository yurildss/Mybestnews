package com.example.mybestnews.screen.feedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.model.Article
import com.example.mybestnews.model.Source
import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject
constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val newsRepository: NewsRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            newsRepository.getAllNews().collect {
                val mutableList = it.map {
                    Article(
                        title = it.title,
                        body = it.description,
                        url = it.url,
                        date = it.publishedAt,
                        source = Source(
                            uri = it.uriSource,
                            title = it.titleSource
                        ),
                        image = ""
                    )
                }
                _uiState.value = _uiState.value.copy(news = mutableList.toMutableList())
            }
        }
    }

    val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()
}

data class FeedScreenUIState(
    val news: MutableList<Article> = mutableListOf()
)