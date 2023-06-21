package com.example.clean.data.mapper

import com.example.clean.data.model.MovieDetailEntity
import com.example.clean.domain.model.MovieDetailModel

object MovieDetailMapper {

    fun MapToDomain(movie: MovieDetailEntity) : MovieDetailModel {
        return MovieDetailModel(
            overview = movie.overview,
            originalTitle = movie.originalTitle,
            video = movie.video,
            title = movie.title,
            genreIds = movie.genreIds,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            releaseDate = movie.releaseDate,
            popularity = movie.popularity,
            voteAverage = movie.voteAverage,
            id = movie.id,
            adult = movie.adult,
            voteCount = movie.voteCount
        )
    }

}