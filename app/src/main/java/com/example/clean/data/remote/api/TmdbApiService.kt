package com.example.clean.data.remote.api

import com.example.clean.data.model.movie.MovieDetailItemEntity
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

    @GET("account/8128796/favorite/movies")
    suspend fun getFavoriteMovie(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("session_id") region: String,
        @Query("sort_by") sortBy: String
    ) : Response<MovieEntity>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ) : Response<MovieDetailItemEntity>

}