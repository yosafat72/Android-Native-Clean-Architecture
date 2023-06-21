package com.example.clean.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val genreIds: List<Int?>? = null,
    val posterPath: String? = null,
    val backdropPath: Any? = null,
    val releaseDate: String? = null,
    val popularity: Any? = null,
    val voteAverage: Float? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null
)
