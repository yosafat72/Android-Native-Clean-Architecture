package com.example.clean.data.remote.api

import com.example.clean.data.model.MovieEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/upcoming")
    suspend fun upcomingMovie(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("region") region: String
    ) : Response<MovieEntity>

}