package com.example.clean.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.usecase.movie.UpcomingMovieUsecase
import com.example.clean.utils.ViewState
import kotlinx.coroutines.launch

class MovieViewModel(private val upcomingMovieUsecase: UpcomingMovieUsecase) : ViewModel() {

    private val _movieListState = MutableLiveData<ViewState>()
    val movieListState: LiveData<ViewState> = _movieListState

    fun loadUpcoming(page: Int){
        _movieListState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val result = upcomingMovieUsecase.execute(page = page)
                if(result.isSuccess){
                    _movieListState.value = result.getOrNull()?.let { ViewState.Success(it) }
                }else{
                    _movieListState.value = result.exceptionOrNull()?.message?.let {
                        ViewState.Error(
                            it
                        )
                    }
                }
            }catch (e: Exception){
                _movieListState.value = ViewState.Error(e.message ?: "Unknown error occurred")
            }

        }
    }

}