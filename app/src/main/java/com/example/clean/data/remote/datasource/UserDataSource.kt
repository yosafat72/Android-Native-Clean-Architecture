package com.example.clean.data.remote.datasource

import com.example.clean.data.model.UserDetailEntity
import com.example.clean.data.remote.api.ApiService
import com.example.clean.data.remote.exception.ApiException

class UserDataSource(private val apiService: ApiService) {

    suspend fun getUsers(page: Int) : List<UserDetailEntity>{
        val response = apiService.getUsers(page = page)
        if(response.isSuccessful){
            return response.body()?.data ?: emptyList()
        }else{
            throw ApiException(code = response.code(), msg = response.message())
        }
    }
}