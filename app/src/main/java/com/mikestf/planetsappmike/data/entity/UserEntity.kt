package com.mikestf.planetsappmike.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikestf.planetsappmike.utils.DATABASE_TABLE
import java.util.Date

@Entity(tableName = DATABASE_TABLE)
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val lastName: String,
    val motherLastName: String,
    val birthDate: Date,
    val country: String,
)