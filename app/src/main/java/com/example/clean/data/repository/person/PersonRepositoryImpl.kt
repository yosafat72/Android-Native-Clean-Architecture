package com.example.clean.data.repository.person

import com.example.clean.data.mapper.person.PersonDetailMapper
import com.example.clean.data.remote.datasource.person.PersonDataSource
import com.example.clean.domain.model.person.PersonDetailModel
import com.example.clean.domain.repository.person.PersonRepository

class PersonRepositoryImpl(private val remoteDataSource: PersonDataSource) : PersonRepository {

    override suspend fun getTrendingPerson(timeWindow: String): List<PersonDetailModel?> {
        val remoteTrendingPerson = remoteDataSource.getTrendingPerson(timeWindow = timeWindow)
        val remoteTrendingPersonDomain = remoteTrendingPerson.map {
            it?.let { it1 -> PersonDetailMapper.MapToDomain(it1) }
        }
        return remoteTrendingPersonDomain
    }

}