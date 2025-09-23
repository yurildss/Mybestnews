package com.example.mybestnews.screen.feedScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.model.Article
import com.example.mybestnews.model.ArticlesRequest
import com.example.mybestnews.repository.UserPreferencesRepository
import com.example.mybestnews.services.NewsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject
constructor(
    private val repository: UserPreferencesRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            val userPreferences = repository.getUserPreferences().catch {
                exception ->
                if(exception is IOException){
                    Log.e("TAG", "Error reading sort order preferences.", exception)
                }else{
                    throw exception
                }
            }

            userPreferences.collect {
                val news = NewsAPI.retrofitService.getNewsByCategoryLanguageCountry(
                    ArticlesRequest(
                    keywords = mapOf("keyword" to it.favoriteTagsList.first()),
                    lang = "eng",
                    page = 1,
                    pageSize =40 ))
                if(news.isSuccessful){
                    val articles = news.body()?.articles?.results
                    val articlesList = mutableListOf<Article>()

                    articles?.forEach {
                        articlesList.add(it)
                    }
                    _uiState.value = _uiState.value.copy(news = articlesList)
                }
                else{
                    Log.e("TAG", "Error fetching news: ${news.errorBody()}")
                }
            }

        }
    }

    val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()
}

data class FeedScreenUIState(
    val news: MutableList<Article> = mutableListOf()
)