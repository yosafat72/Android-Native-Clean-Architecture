package com.example.clean.domain.repository.person

import com.example.clean.domain.model.person.PersonDetailModel

interface PersonRepository {
    suspend fun getTrendingPerson(timeWindow: String) : List<PersonDetailModel?>
}