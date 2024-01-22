package com.mikestf.planetsappmike.domain.use_case

import com.mikestf.planetsappmike.domain.model.User
import com.mikestf.planetsappmike.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor (
    private val repository: UserRepository
) {
    suspend fun getUsers(): Flow<List<User>> {
        return repository.getAllUsers()
    }
}