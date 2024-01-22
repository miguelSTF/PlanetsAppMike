package com.mikestf.planetsappmike.ui.detail_planet.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikestf.planetsappmike.data.Result
import com.mikestf.planetsappmike.domain.use_case.GetPlanetDetailUseCase
import com.mikestf.planetsappmike.ui.detail_planet.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    var state by mutableStateOf(DetailState(isLoading = true))
        private set

    init {
        getPlanetDetail()
    }

    private fun getPlanetDetail() {
        savedStateHandle.get<String>("id")?.let { planetId ->
            viewModelScope.launch {
                getPlanetDetailUseCase(planetId).also { result ->
                    when (result) {
                        is Result.Success -> {
                            state = state.copy(
                                detailPlanet = result.data,
                                isLoading = false
                            )
                        }
                        is Result.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                        }
                        is Result.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}