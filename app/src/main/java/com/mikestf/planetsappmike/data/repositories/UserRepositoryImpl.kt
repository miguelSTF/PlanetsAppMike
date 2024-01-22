package com.mikestf.planetsappmike.data.repositories

import com.mikestf.planetsappmike.data.UserDao
import com.mikestf.planetsappmike.data.mapper.toUser
import com.mikestf.planetsappmike.data.mapper.toUserEntity
import com.mikestf.planetsappmike.domain.model.User
import com.mikestf.planetsappmike.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: User) {
        dao.insertUser(user.toUserEntity())
    }

    override fun getAllUsers(): Flow<List<User>> {
        return dao.getAllUsers().map { entities ->
            entities.map { it.toUser() }
        }
    }
}