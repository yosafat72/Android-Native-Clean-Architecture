package com.example.clean.domain.repository

import com.example.clean.domain.model.MovieDetailModel

interface MovieRepository {

    suspend fun upcomingMovie(page: Int) : List<MovieDetailModel?>

}