package com.mikestf.planetsappmike.data.source.dto.listPlanets

import com.mikestf.planetsappmike.domain.model.Planets

data class ListPlanetDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)

fun ListPlanetDto.toListPlanets(): List<Planets> {
    val resultConvert = results.mapIndexed { _, entries ->
        Planets(
            name = entries.name,
            gravity = entries.gravity,
            population = entries.population,
            terrain = entries.terrain,
            rotationPeriod = entries.rotation_period,
            url = entries.url
        )
    }
    return resultConvert
}