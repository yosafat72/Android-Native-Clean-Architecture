package com.example.clean.di

import com.example.clean.data.remote.api.TmdbApiService
import com.example.clean.data.remote.datasource.movie.MovieDataSource
import com.example.clean.data.remote.datasource.movie.MovieDetailDataSource
import com.example.clean.data.remote.datasource.person.PersonDataSource
import com.example.clean.data.remote.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val tmdbNetworkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createTmdbApiService(get()) }
    single { HeaderInterceptor() }
    single { MovieDataSource(get()) }
    single { PersonDataSource(get()) }
    single { MovieDetailDataSource(get()) }
}

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
    return builder.build()
}

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/") // Base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createTmdbApiService(retrofit: Retrofit): TmdbApiService {
    return retrofit.create(TmdbApiService::class.java)
}