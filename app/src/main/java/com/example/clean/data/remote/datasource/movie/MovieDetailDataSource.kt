package com.example.clean.data.remote.datasource.movie

import com.example.clean.data.model.movie.MovieDetailItemEntity
import com.example.clean.data.remote.api.TmdbApiService
import com.example.clean.data.remote.exception.ApiException

class MovieDetailDataSource(private val tmdbApiService: TmdbApiService) {

    suspend fun getDetailMovie(movieId: Int) : MovieDetailItemEntity?{
        val response = tmdbApiService.getMovieDetails(language = "en-US", movieId = movieId)
        if(response.isSuccessful){
            return response.body()
        }else{
            throw ApiException(code = response.code(), msg = response.message())
        }

    }

}