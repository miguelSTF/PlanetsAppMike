package com.mikestf.planetsappmike.domain.repositories

import com.mikestf.planetsappmike.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)

    fun getAllUsers(): Flow<List<User>>
}