package com.example.clean.domain.usecase.movie

import com.example.clean.domain.model.movie.MovieDetailItemModel
import com.example.clean.domain.repository.movie.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailMovieUsecase(private val repository: MovieDetailRepository) {

    suspend fun execute(movieId: Int) : Result<MovieDetailItemModel?>{
        return withContext(Dispatchers.IO){
            try {
                val movies = repository.detailMovie(movieId = movieId)
                Result.success(movies)
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}