package com.mikestf.planetsappmike.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User(
    val id: Long?,
    val name: String,
    val lastName: String,
    val motherLastName: String,
    val birthDate: Date,
    val country: String
) : Parcelable