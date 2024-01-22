package com.mikestf.planetsappmike.ui.list_planets.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mikestf.planetsappmike.domain.model.Planets
import com.mikestf.planetsappmike.ui.list_planets.components.PlanetsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlanetsViewModel @Inject constructor(
    private val planetsPagingSource: PlanetsPagingSource
) : ViewModel() {

    private val _planetResponse: MutableStateFlow<PagingData<Planets>> =
        MutableStateFlow(PagingData.empty())
    var planetResponse = _planetResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    6, enablePlaceholders = true
                )
            ) {
                planetsPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _planetResponse.value = it
            }
        }
    }
}