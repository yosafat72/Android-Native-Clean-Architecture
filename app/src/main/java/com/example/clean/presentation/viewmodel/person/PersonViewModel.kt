package com.example.clean.presentation.viewmodel.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.usecase.person.TrendingPersonUsecase
import com.example.clean.utils.ViewState
import kotlinx.coroutines.launch

class PersonViewModel(private val personUsecase: TrendingPersonUsecase) : ViewModel() {

    private val _personTrendingListState = MutableLiveData<ViewState>()
    val personTrendingListState: LiveData<ViewState> = _personTrendingListState

    fun loadTrendingPerson(timeWindow: String){
        _personTrendingListState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val result = personUsecase.execute(timeWindow = timeWindow)
                if(result.isSuccess){
                    _personTrendingListState.value = result.getOrNull()?.let { ViewState.Success(it) }
                }else{
                    _personTrendingListState.value = result.exceptionOrNull()?.message?.let {
                        ViewState.Error(
                            it
                        )
                    }
                }
            }catch (e: Exception){
                _personTrendingListState.value = ViewState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

}