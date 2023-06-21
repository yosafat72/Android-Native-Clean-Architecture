package com.example.clean.di

import com.example.clean.data.repository.MovieRepositoryImpl
import com.example.clean.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}