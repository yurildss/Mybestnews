package com.example.mybestnews.repository

import com.example.mybestnews.model.OfflineNews
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

   fun getAllNews():Flow<List<OfflineNews>>
   suspend fun insertNews(news: OfflineNews)

}