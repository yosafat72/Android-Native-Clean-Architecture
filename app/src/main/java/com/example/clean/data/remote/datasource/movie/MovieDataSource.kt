package com.example.clean.data.remote.datasource.movie

import com.example.clean.data.model.movie.MovieDetailEntity
import com.example.clean.data.remote.api.TmdbApiService
import com.example.clean.data.remote.exception.ApiException

class MovieDataSource(private val tmdbApiService: TmdbApiService) {

    suspend fun getUpcoming(page: Int) : List<MovieDetailEntity?> {
        val response = tmdbApiService.upcomingMovie(page = page, language = "en-US", region = "")
        if(response.isSuccessful){
            return response.body()?.results ?: emptyList()
        }else{
            throw ApiException(code = response.code(), msg = response.message())
        }
    }

    suspend fun getNowPlaying(page: Int) : List<MovieDetailEntity?> {
        val response = tmdbApiService.nowPlaying(page = page, language = "en-US", region = "")
        if(response.isSuccessful){
            return response.body()?.results ?: emptyList()
        }else{
            throw ApiException(code = response.code(), msg = response.message())
        }
    }

}