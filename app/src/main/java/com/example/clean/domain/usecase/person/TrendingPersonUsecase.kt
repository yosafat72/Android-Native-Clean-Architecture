package com.example.clean.domain.usecase.person

import com.example.clean.domain.model.person.PersonDetailModel
import com.example.clean.domain.repository.person.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingPersonUsecase(private val repository: PersonRepository) {

    suspend fun execute(timeWindow: String) : Result<List<PersonDetailModel?>> {
        return withContext(Dispatchers.IO){
            try {
                val persons = repository.getTrendingPerson(timeWindow = timeWindow)
                Result.success(persons)
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}