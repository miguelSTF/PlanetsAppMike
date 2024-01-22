package com.mikestf.planetsappmike.data.source.dto.detailPlanet

import com.mikestf.planetsappmike.domain.model.DetailPlanet

data class DetailPlanetDto(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String
)

fun DetailPlanetDto.toDetailPlanet(): DetailPlanet {
    return DetailPlanet(
        name = name,
        climate = climate,
        population = population,
        terrain = terrain,
        url = url
    )
}