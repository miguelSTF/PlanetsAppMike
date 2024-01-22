package com.mikestf.planetsappmike.domain.use_case

import com.mikestf.planetsappmike.data.Result
import com.mikestf.planetsappmike.domain.model.Planets
import com.mikestf.planetsappmike.domain.repositories.PlanetRepository
import javax.inject.Inject

class GetPlanetListUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Planets>> {
        return repository.getPlanets(page)
    }
}