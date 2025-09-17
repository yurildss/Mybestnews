package com.example.mybestnews.services

import com.example.mybestnews.model.ArticlesRequest
import com.example.mybestnews.model.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://eventregistry.org/api/v1/"

private val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val client = OkHttpClient
    .Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

object NewsAPI {
    val retrofitService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}

interface NewsAPIService {
    @POST("article/getArticles")
    suspend fun getNewsByCategoryLanguageCountry(
        @Body request: ArticlesRequest
    ) : Response<NewsResponse>
}
