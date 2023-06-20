package com.example.clean.domain.repository

import com.example.clean.data.model.UserDetailEntity

interface UserRepository {
    suspend fun getUsers(page: Int) : List<UserDetailEntity>
}