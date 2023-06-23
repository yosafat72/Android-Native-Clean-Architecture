package com.example.clean.di

import com.example.clean.domain.usecase.movie.GetFavoriteMovieUsecase
import com.example.clean.domain.usecase.movie.NowPlayingMovieUsecase
import com.example.clean.domain.usecase.movie.UpcomingMovieUsecase
import com.example.clean.domain.usecase.person.TrendingPersonUsecase
import org.koin.dsl.module

val usecaseModule = module {
    factory { UpcomingMovieUsecase(get()) }
    factory { NowPlayingMovieUsecase(get()) }
    factory { TrendingPersonUsecase(get()) }
    factory { GetFavoriteMovieUsecase(get()) }
}