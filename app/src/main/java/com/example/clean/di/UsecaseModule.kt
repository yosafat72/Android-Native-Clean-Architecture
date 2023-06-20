package com.example.clean.di

import com.example.clean.domain.usecase.GetUsersUsecase
import org.koin.dsl.module

val usecaseModule = module {
    factory { GetUsersUsecase(get()) }
}