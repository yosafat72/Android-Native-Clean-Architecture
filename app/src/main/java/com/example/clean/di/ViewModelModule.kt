package com.example.clean.di

import com.example.clean.presentation.viewmodel.movie.MovieViewModel
import com.example.clean.presentation.viewmodel.person.PersonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
    viewModel { PersonViewModel(get()) }
}