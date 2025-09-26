package com.example.mybestnews.work

import android.content.Context
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mybestnews.model.ArticlesRequest
import com.example.mybestnews.model.OfflineNews
import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.UserPreferencesRepository
import com.example.mybestnews.services.NewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okio.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DownloadWorker
@Inject constructor(
    appContext: Context,
    workerParams: WorkerParameters,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val newsRepository: NewsRepository
): Worker(appContext, workerParams)
{

    val downLoadRequest: WorkRequest = PeriodicWorkRequestBuilder<DownloadWorker>(12, TimeUnit.HOURS)
        .build()

    val workManager = WorkManager.getInstance(appContext).enqueue(downLoadRequest)

    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            val userPreferences = userPreferencesRepository.getUserPreferences().catch {
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
                        pageSize =40 )
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
        }

        return Result.success()
    }

}