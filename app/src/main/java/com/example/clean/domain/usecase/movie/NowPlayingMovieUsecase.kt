package com.example.clean.domain.usecase.movie

import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.domain.repository.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NowPlayingMovieUsecase(private val repository: MovieRepository) {

    suspend fun execute(page: Int) : Result<List<MovieDetailModel?>> {
        return withContext(Dispatchers.IO){
            try {
                val movies = repository.nowPlayingMovie(page = page)
                Result.success(movies)
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}