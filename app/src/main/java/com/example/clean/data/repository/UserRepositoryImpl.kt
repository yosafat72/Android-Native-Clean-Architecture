package com.example.clean.data.repository

import android.util.Log
import com.example.clean.data.mapper.UserDetailMapper
import com.example.clean.data.model.UserDetailEntity
import com.example.clean.data.remote.datasource.UserDataSource
import com.example.clean.domain.model.UserDetailModel
import com.example.clean.domain.repository.UserRepository

class UserRepositoryImpl(private val remoteDataSource: UserDataSource) : UserRepository {

    override suspend fun getUsers(page: Int): List<UserDetailModel> {
        val remoteUsers = remoteDataSource.getUsers(page = page)
        val remoteUsersDomain = remoteUsers.map {
            UserDetailMapper.MapToDomain(it)
        }
        return remoteUsersDomain
    }

}