package com.example.mybestnews.services

import com.example.mybestnews.model.NewsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://eventregistry.org/api/v1/"

private val client = OkHttpClient
    .Builder()
    .addInterceptor(HeaderInterceptor())
    .build()

private val retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object NewsAPI {
    val retrofitService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}

interface NewsAPIService {
    @GET("news")
    suspend fun getNewsByCategoryLanguageCountry(
        @Query("q") query: String?,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
    ) : List<NewsResponse>
}
