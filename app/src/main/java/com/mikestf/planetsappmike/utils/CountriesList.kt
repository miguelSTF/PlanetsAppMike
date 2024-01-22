package com.mikestf.planetsappmike.utils

enum class Countries {
    México,
    Argentina,
    España,
    Colombia
}

fun getCountriesList(): List<Countries> {
    val countriesList = mutableListOf<Countries>()
    countriesList.add(Countries.México)
    countriesList.add(Countries.Argentina)
    countriesList.add(Countries.España)
    countriesList.add(Countries.Colombia)
    return countriesList
}