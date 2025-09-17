package com.example.mybestnews.services

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", "86527cc2-7ace-46e5-a855-74b4f517a2a9")
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        Log.d("API_REQUEST", "URL: ${newUrl.url()}")
        return chain.proceed(newRequest)
    }
}