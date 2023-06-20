package com.example.clean.data.repository

import com.example.clean.data.model.UserDetailEntity
import com.example.clean.data.remote.datasource.UserDataSource
import com.example.clean.domain.repository.UserRepository

class UserRepositoryImpl(private val remoteDataSource: UserDataSource) : UserRepository {

    override suspend fun getUsers(page: Int): List<UserDetailEntity> {
        return remoteDataSource.getUsers(page = page)
    }

}