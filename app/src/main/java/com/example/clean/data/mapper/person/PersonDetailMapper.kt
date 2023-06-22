package com.example.clean.data.mapper.person

import com.example.clean.data.model.person.PersonDetailEntity
import com.example.clean.domain.model.person.PersonDetailModel

object PersonDetailMapper {

    fun MapToDomain(person: PersonDetailEntity) : PersonDetailModel {
        return PersonDetailModel(
            name = person.name,
            profilePath = person.profilePath
        )
    }

}