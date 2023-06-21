package com.example.clean.data.mapper

import com.example.clean.data.model.UserDetailEntity
import com.example.clean.domain.model.UserDetailModel

object UserDetailMapper {

    fun MapToDomain(user: UserDetailEntity) : UserDetailModel{
        return UserDetailModel(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            avatar = user.avatar
        )
    }

    fun MapToData(user: UserDetailModel) : UserDetailEntity{
        return UserDetailEntity(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            avatar = user.avatar
        )
    }

}