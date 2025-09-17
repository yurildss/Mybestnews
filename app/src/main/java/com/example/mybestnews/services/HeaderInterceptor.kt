package com.example.mybestnews.services

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("apiKey", "9d286ea9311d4aa1b53cc1f982694f31")
            .build()
        return chain.proceed(request)
    }
}