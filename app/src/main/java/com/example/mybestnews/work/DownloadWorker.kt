package com.example.mybestnews.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mybestnews.model.ArticlesRequest
import com.example.mybestnews.model.OfflineNews
import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.UserPreferencesRepository
import com.example.mybestnews.services.NewsAPI
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okio.IOException

class DownloadWorker
@AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val newsRepository: NewsRepository
): CoroutineWorker(appContext, workerParams)
{

    override suspend fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {

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
                        keywordOper = "or")
                )
                if(news.isSuccessful){
                    val articles = news.body()?.articles?.results


                    articles?.forEach {
                        newsRepository.insertNews(OfflineNews(
                            title = it.title,
                            description = it.body.toString(),
                            url = it.url,
                            publishedAt = it.date,
                            uriSource = it.source?.uri.toString(),
                            titleSource = it.source?.title.toString()
                        ))
                    }
                }
                else{
                    Log.e("TAG", "Error fetching news: ${news.errorBody()}")
                }

        }

        return Result.success()
    }

}