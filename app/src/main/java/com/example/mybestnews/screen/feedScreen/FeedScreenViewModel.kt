package com.example.mybestnews.screen.feedScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.model.Article
import com.example.mybestnews.model.ArticlesRequest
import com.example.mybestnews.model.Source
import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.UserPreferencesRepository
import com.example.mybestnews.services.NewsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject
constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
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

                if (mutableList.isEmpty()){
                    val newsFromApi = getNewsFromApi()
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(news = newsFromApi)
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(news = mutableList.toMutableList())
                    }
                }
            }
        }
    }

    val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()

    suspend fun getNewsFromApi(): MutableList<Article> {
        val mutableList: MutableList<Article> = mutableListOf()
        val userPreferences = userPreferencesRepository
            .getUserPreferences()
            .catch {
                    exception ->
                if(exception is IOException){
                    Log.e("TAG", "Error reading sort order preferences.", exception)
                }else{
                    throw exception
                }
            }.first()

        val news = NewsAPI.retrofitService.getNewsByCategoryLanguageCountry(
            ArticlesRequest(
                keyword = userPreferences.favoriteTagsList,
                lang = "eng",
                page = 1,
                pageSize =40,
                keywordOper = "or"
            )
        )
        if(news.isSuccessful){
            val articles = news.body()?.articles?.results
            articles?.forEach {
                mutableList.add(it)
            }
        }
        else{
            Log.e("TAG", "Error fetching news: ${news.errorBody()}")
        }
        return mutableList
    }
}

data class FeedScreenUIState(
    val news: MutableList<Article> = mutableListOf()
)