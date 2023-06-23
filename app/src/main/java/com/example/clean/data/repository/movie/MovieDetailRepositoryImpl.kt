package com.example.clean.data.repository.movie

import com.example.clean.data.mapper.movie.MovieDetailItemMapper
import com.example.clean.data.remote.datasource.movie.MovieDetailDataSource
import com.example.clean.domain.model.movie.MovieDetailItemModel
import com.example.clean.domain.repository.movie.MovieDetailRepository

class MovieDetailRepositoryImpl(private val remoteDataSource: MovieDetailDataSource) : MovieDetailRepository {

    override suspend fun detailMovie(movieId: Int): MovieDetailItemModel? {
        val remoteDetailMovie = remoteDataSource.getDetailMovie(movieId = movieId)
        return remoteDetailMovie?.let { MovieDetailItemMapper.MapToDomain(item = it) }
    }

}