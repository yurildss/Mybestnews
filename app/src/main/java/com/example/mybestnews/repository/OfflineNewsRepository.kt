package com.example.mybestnews.repository

import com.example.mybestnews.model.OfflineNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class OfflineNewsRepository @Inject constructor(
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getAllNews(): Flow<List<OfflineNews>> = newsDao.getNews()
    override suspend fun insertNews(news: OfflineNews) = newsDao.insert(news)

}