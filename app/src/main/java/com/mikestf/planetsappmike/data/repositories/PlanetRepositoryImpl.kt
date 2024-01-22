package com.mikestf.planetsappmike.data.repositories

import com.mikestf.planetsappmike.data.Result
import com.mikestf.planetsappmike.data.source.dto.detailPlanet.toDetailPlanet
import com.mikestf.planetsappmike.data.source.dto.listPlanets.toListPlanets
import com.mikestf.planetsappmike.data.source.remote.PlanetsMikeApi
import com.mikestf.planetsappmike.domain.model.DetailPlanet
import com.mikestf.planetsappmike.domain.model.Planets
import com.mikestf.planetsappmike.domain.repositories.PlanetRepository
import com.mikestf.planetsappmike.utils.INTERNET_ERROR
import com.mikestf.planetsappmike.utils.MESSAGE_ERROR
import com.mikestf.planetsappmike.utils.UNKNOWN_ERROR
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/*
* Implementación del planets repository
*/
class PlanetRepositoryImpl @Inject constructor (
    private val api: PlanetsMikeApi
): PlanetRepository {
    override suspend fun getPlanets(page: Int): Result<List<Planets>> {
        val response = try {
             api.getListPlanets(page).toListPlanets()
        } catch (e: HttpException) {
            return Result.Error(
                message = MESSAGE_ERROR,
                data = null
            )
        } catch (e: IOException) {
            return Result.Error(
                message = INTERNET_ERROR,
                data = null
            )
        }
        return Result.Success(response)
    }

    override suspend fun getDetailPlanet(id: String): Result<DetailPlanet> {
        val response = try {
            api.getDetailPlanet(id)
        } catch (e: Exception) {
            return Result.Error(UNKNOWN_ERROR)
        }
        return Result.Success(response.toDetailPlanet())
    }
}