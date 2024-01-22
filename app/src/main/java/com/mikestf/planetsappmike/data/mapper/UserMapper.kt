package com.mikestf.planetsappmike.data.mapper

import com.mikestf.planetsappmike.data.entity.UserEntity
import com.mikestf.planetsappmike.domain.model.User

fun UserEntity.toUser(): User {
    return User (
        id = id,
        name = name,
        lastName = lastName,
        motherLastName = motherLastName,
        birthDate = birthDate,
        country = country
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id ?: 0L,
        name = name,
        lastName = lastName,
        motherLastName = motherLastName,
        birthDate = birthDate,
        country = country
    )
}