package com.example.clean.di

import com.example.clean.data.remote.api.ApiService
import com.example.clean.data.remote.datasource.UserDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    // Retrofit
    single { createOkHttpClient() }
    single { createRetrofit(get()) }

    // Api Service
    single { createApiService(get()) }

    // Remote Data Source
    single { UserDataSource(get()) }

}

// Function to create OkHttpClient
private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    // Add any necessary configurations, such as interceptors, timeouts, etc.
    return builder.build()
}

// Function to create Retrofit
private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://reqres.in/") // Base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

// Function to create API Service
private fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}