package com.example.clean.data.repository

import com.example.clean.data.mapper.MovieDetailMapper
import com.example.clean.data.remote.datasource.MovieDataSource
import com.example.clean.domain.model.MovieDetailModel
import com.example.clean.domain.repository.MovieRepository

class MovieRepositoryImpl(private val remoteDataSource: MovieDataSource) : MovieRepository {

    override suspend fun upcomingMovie(page: Int): List<MovieDetailModel?> {
        val remoteUsers = remoteDataSource.getUpcoming(page = page)
        val remoteUsersDomain = remoteUsers.map {
            it?.let { it1 -> MovieDetailMapper.MapToDomain(it1) }
        }
        return remoteUsersDomain
    }

}