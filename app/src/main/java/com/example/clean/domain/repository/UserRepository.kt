package com.example.clean.domain.repository

import com.example.clean.domain.model.UserDetailModel

interface UserRepository {
    suspend fun getUsers(page: Int) : List<UserDetailModel>
}