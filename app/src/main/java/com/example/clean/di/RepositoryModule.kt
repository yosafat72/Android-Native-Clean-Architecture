package com.example.clean.di

import com.example.clean.data.repository.MovieRepositoryImpl
import com.example.clean.data.repository.UserRepositoryImpl
import com.example.clean.domain.repository.MovieRepository
import com.example.clean.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}