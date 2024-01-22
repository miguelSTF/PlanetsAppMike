package com.mikestf.planetsappmike.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return sdf.format(this)
}