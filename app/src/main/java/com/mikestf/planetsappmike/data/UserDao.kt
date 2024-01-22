package com.mikestf.planetsappmike.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mikestf.planetsappmike.data.entity.UserEntity
import com.mikestf.planetsappmike.utils.DATABASE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity): Long

    @Query("SELECT * FROM $DATABASE_TABLE")
    fun getAllUsers(): Flow<List<UserEntity>>
}