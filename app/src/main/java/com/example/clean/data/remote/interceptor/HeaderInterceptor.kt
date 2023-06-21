package com.example.clean.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NWNkYjc4ZThiMTM5MmVjZTMzYWI5NDU2NDhlYTFhMiIsInN1YiI6IjViZTk0NmU5MGUwYTI2M2MwMjAzYmYxNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VIkvrZoIdxNHW9CBYIEC7QUUJnXwX9BQktK2BBSjsn8")
            .build()

        return chain.proceed(newRequest)
    }

}