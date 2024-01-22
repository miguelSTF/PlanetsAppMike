package com.mikestf.planetsappmike.data.source.remote

import com.mikestf.planetsappmike.data.source.dto.detailPlanet.DetailPlanetDto
import com.mikestf.planetsappmike.data.source.dto.listPlanets.ListPlanetDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetsMikeApi {
    @GET("planets")
    suspend fun getListPlanets(
        @Query("page") page: Int
    ) : ListPlanetDto

    @GET("planets/{id}")
    suspend fun getDetailPlanet(
        @Path("id") id: String
    ): DetailPlanetDto
}