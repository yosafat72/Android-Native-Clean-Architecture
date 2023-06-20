package com.example.clean.data.remote.api

import com.example.clean.data.model.UserEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ) : Response<UserEntity>

}