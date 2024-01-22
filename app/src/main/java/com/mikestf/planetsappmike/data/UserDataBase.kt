package com.mikestf.planetsappmike.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mikestf.planetsappmike.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDao: UserDao
}