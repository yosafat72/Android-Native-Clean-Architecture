package com.example.clean.data.repository.movie

import com.example.clean.data.mapper.movie.MovieDetailMapper
import com.example.clean.data.remote.datasource.movie.MovieDataSource
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.domain.repository.movie.MovieRepository

class MovieRepositoryImpl(private val remoteDataSource: MovieDataSource) : MovieRepository {

    override suspend fun upcomingMovie(page: Int): List<MovieDetailModel?> {
        val remoteUpcomingMovie = remoteDataSource.getUpcoming(page = page)
        val remoteUpcomingMovieDomain = remoteUpcomingMovie.map {
            it?.let { it1 -> MovieDetailMapper.MapToDomain(it1) }
        }
        return remoteUpcomingMovieDomain
    }

    override suspend fun nowPlayingMovie(page: Int): List<MovieDetailModel?> {
        val remoteNowPlaying = remoteDataSource.getNowPlaying(page = page)
        val remoteNowPlayingDomain = remoteNowPlaying.map {
            it?.let { it1 -> MovieDetailMapper.MapToDomain(it1) }
        }
        return remoteNowPlayingDomain
    }

}