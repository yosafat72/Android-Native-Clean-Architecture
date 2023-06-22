package com.example.clean.data.remote.api

import com.example.clean.data.model.movie.MovieEntity
import com.example.clean.data.model.person.PersonEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/upcoming")
    suspend fun upcomingMovie(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("region") region: String
    ) : Response<MovieEntity>

    @GET("movie/now_playing")
    suspend fun nowPlaying(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("region") region: String
    ) : Response<MovieEntity>

    @GET("trending/person/{time_window}")
    suspend fun trendingPerson(
        @Path("time_window") timeWindow: String
    ) : Response<PersonEntity>

}