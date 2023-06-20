package com.example.clean.domain.usecase

import com.example.clean.data.model.UserDetailEntity
import com.example.clean.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsersUsecase(private val repository: UserRepository){

    suspend fun execute(page: Int) : Result<List<UserDetailEntity>> {
        return withContext(Dispatchers.IO){
            try {
                val users = repository.getUsers(page = page)
                Result.success(users)
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}