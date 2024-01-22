package com.mikestf.planetsappmike.domain.repositories

import com.mikestf.planetsappmike.data.Result
import com.mikestf.planetsappmike.domain.model.DetailPlanet
import com.mikestf.planetsappmike.domain.model.Planets

interface PlanetRepository {
    suspend fun getPlanets(page: Int): Result<List<Planets>>

    suspend fun getDetailPlanet(id: String): Result<DetailPlanet>
}