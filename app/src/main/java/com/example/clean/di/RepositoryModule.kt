package com.example.clean.di

import com.example.clean.data.repository.movie.MovieDetailRepositoryImpl
import com.example.clean.data.repository.movie.MovieRepositoryImpl
import com.example.clean.data.repository.person.PersonRepositoryImpl
import com.example.clean.domain.repository.movie.MovieDetailRepository
import com.example.clean.domain.repository.movie.MovieRepository
import com.example.clean.domain.repository.person.PersonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<PersonRepository> { PersonRepositoryImpl(get()) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
}