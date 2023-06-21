package com.example.clean.di

import com.example.clean.domain.usecase.GetUsersUsecase
import com.example.clean.domain.usecase.movie.UpcomingMovieUsecase
import org.koin.dsl.module

val usecaseModule = module {
    factory { GetUsersUsecase(get()) }
    factory { UpcomingMovieUsecase(get()) }
}