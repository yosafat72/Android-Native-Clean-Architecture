package com.example.clean.utils

sealed class ViewState{
    object Loading : ViewState()
    data class Success(val data: Any) : ViewState()
    data class Error(val error: String) : ViewState()
}
