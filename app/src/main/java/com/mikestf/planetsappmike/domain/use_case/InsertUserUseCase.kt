package com.mikestf.planetsappmike.domain.use_case

import com.mikestf.planetsappmike.domain.model.User
import com.mikestf.planetsappmike.domain.repositories.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor (
    private val repository: UserRepository
) {
    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }
}