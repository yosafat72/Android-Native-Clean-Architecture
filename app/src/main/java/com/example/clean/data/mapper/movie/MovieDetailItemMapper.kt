package com.example.clean.data.mapper.movie

import com.example.clean.data.model.movie.MovieDetailItemEntity
import com.example.clean.domain.model.movie.*

object MovieDetailItemMapper {

    fun MapToDomain(item : MovieDetailItemEntity) : MovieDetailItemModel {
        return MovieDetailItemModel(
            originalLanguage = item.originalLanguage,
            imdbId = item.imdbId,
            video = item.video,
            title = item.title,
            backdropPath = item.backdropPath,
            revenue = item.revenue,
            genres = MapListGenresItemToDomain(items = item.genres),
            popularity = item.popularity,
            productionCountries = MapListProductCountriesItemToDomain(items = item.productionCountries),
            id = item.id,
            voteCount = item.voteCount,
            budget = item.budget,
            overview = item.overview,
            originalTitle = item.originalTitle,
            runtime = item.runtime,
            posterPath = item.posterPath,
            productionCompanies = MapListProductionCompaniesToDomain(item.productionCompanies),
            releaseDate = item.releaseDate,
            voteAverage = item.voteAverage,
            belongsToCollection = item.belongsToCollection,
            tagline = item.tagline,
            adult = item.adult,
            homepage = item.homepage,
            status = item.status
        )
    }

    fun MapListGenresItemToDomain(items: List<com.example.clean.data.model.movie.GenresItem?>?) : List<GenresItem?>?{
        return items?.map {
            GenresItem(
                id = it?.id,
                name = it?.name
            )
        }
    }

    fun MapListProductCountriesItemToDomain(items: List<com.example.clean.data.model.movie.ProductionCountriesItem?>?) : List<ProductionCountriesItem?>? {
        return items?.map {
            ProductionCountriesItem(
                iso31661 = it?.iso31661,
                name = it?.name
            )
        }
    }

    fun MapListProductionCompaniesToDomain(items : List<com.example.clean.data.model.movie.ProductionCompaniesItem?>?) : List<ProductionCompaniesItem?>? {
        return items?.map {
            ProductionCompaniesItem(
                id = it?.id,
                logoPath = it?.logoPath,
                name = it?.name,
                originCountry = it?.originCountry
            )
        }
    }

}