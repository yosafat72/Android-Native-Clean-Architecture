package com.example.clean.domain.repository.movie

import com.example.clean.domain.model.movie.MovieDetailItemModel

interface MovieDetailRepository {

    suspend fun detailMovie(movieId: Int) : MovieDetailItemModel?

}