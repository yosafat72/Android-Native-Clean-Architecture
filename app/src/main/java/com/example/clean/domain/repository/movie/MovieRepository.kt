package com.example.clean.domain.repository.movie

import com.example.clean.domain.model.movie.MovieDetailModel

interface MovieRepository {

    suspend fun upcomingMovie(page: Int) : List<MovieDetailModel?>
    suspend fun nowPlayingMovie(page: Int) : List<MovieDetailModel?>

}