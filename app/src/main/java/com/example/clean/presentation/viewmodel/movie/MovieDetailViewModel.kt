package com.example.clean.presentation.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.usecase.movie.DetailMovieUsecase
import com.example.clean.utils.ViewState
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val detailMovieUsecase: DetailMovieUsecase) : ViewModel() {

    private val _movieDetailState = MutableLiveData<ViewState>()
    val movieDetailState: LiveData<ViewState> = _movieDetailState

    fun detailMovie(movieId: Int){
        _movieDetailState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val result = detailMovieUsecase.execute(movieId = movieId)
                if(result.isSuccess){
                    _movieDetailState.value = result.getOrNull()?.let { ViewState.Success(it) }
                }else{
                    _movieDetailState.value = result.exceptionOrNull()?.message?.let {
                        ViewState.Error(
                            it
                        )
                    }
                }
            }catch (e: Exception){
                _movieDetailState.value = ViewState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

}