package com.example.mybestnews.ui.theme

import com.example.mybestnews.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL =
    "https://newsapi.org/v2/"

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory()
    .baseUrl(BASE_URL)
    .build()

interface NewsAPIService {
    @GET("top-headlines")
    suspend fun getNewsByCategoryLanguageCountry(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String
    ) : List<NewsResponse>
}