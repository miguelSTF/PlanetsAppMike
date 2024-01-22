package com.mikestf.planetsappmike.ui.detail_planet

import com.mikestf.planetsappmike.domain.model.DetailPlanet

data class DetailState (
    val detailPlanet: DetailPlanet? = null,
    val isLoading: Boolean = false
)