package com.mikestf.planetsappmike.domain.use_case

import com.mikestf.planetsappmike.data.Result
import com.mikestf.planetsappmike.domain.model.DetailPlanet
import com.mikestf.planetsappmike.domain.repositories.PlanetRepository
import javax.inject.Inject

/*
* Caso de uso para obtener el detalle de un planeta
*/
class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: String): Result<DetailPlanet> {
        return repository.getDetailPlanet(id)
    }
}