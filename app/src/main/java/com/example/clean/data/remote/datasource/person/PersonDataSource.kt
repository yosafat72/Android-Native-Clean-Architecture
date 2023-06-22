package com.example.clean.data.remote.datasource.person

import com.example.clean.data.model.person.PersonDetailEntity
import com.example.clean.data.remote.api.TmdbApiService
import com.example.clean.data.remote.exception.ApiException

class PersonDataSource(private val tmdbApiService: TmdbApiService) {

    suspend fun getTrendingPerson(timeWindow: String) : List<PersonDetailEntity?> {
        val response = tmdbApiService.trendingPerson(timeWindow = timeWindow)
        if(response.isSuccessful){
            return response.body()?.results ?: emptyList()
        }else{
            throw ApiException(code = response.code(), msg = response.message())
        }
    }

}