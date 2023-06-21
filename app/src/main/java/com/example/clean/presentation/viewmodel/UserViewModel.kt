package com.example.clean.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.usecase.GetUsersUsecase
import com.example.clean.utils.ViewState
import kotlinx.coroutines.launch

class UserViewModel(private val getUsersUsecase: GetUsersUsecase) : ViewModel() {

    private val _userListState = MutableLiveData<ViewState>()
    val userListState: LiveData<ViewState> = _userListState

    fun loadUsers(page: Int){
        _userListState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val result = getUsersUsecase.execute(page = page)
                if(result.isSuccess){
                    _userListState.value = result.getOrNull()?.let { ViewState.Success(it) }
                }else{
                    _userListState.value = result.exceptionOrNull()?.message?.let {
                        ViewState.Error(
                            it
                        )
                    }
                }
            }catch (e: Exception){
                _userListState.value = ViewState.Error(e.message ?: "Unknown error occurred")
            }

        }
    }

}