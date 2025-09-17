package com.example.mybestnews.services

import com.example.mybestnews.model.NewsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"

private val client = OkHttpClient
    .Builder()
    .addInterceptor(HeaderInterceptor())
    .build()

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

object NewsAPI {
    val retrofitService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}

interface NewsAPIService {
    @GET("top-headlines")
    suspend fun getNewsByCategoryLanguageCountry(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String
    ) : List<NewsResponse>
}
