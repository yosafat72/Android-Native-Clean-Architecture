package com.example.clean.domain.usecase.movie

import com.example.clean.domain.model.MovieDetailModel
import com.example.clean.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpcomingMovieUsecase(private val repository: MovieRepository) {

    suspend fun execute(page: Int) : Result<List<MovieDetailModel?>> {
        return withContext(Dispatchers.IO){
            try {
                val movies = repository.upcomingMovie(page = page)
                Result.success(movies)
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}