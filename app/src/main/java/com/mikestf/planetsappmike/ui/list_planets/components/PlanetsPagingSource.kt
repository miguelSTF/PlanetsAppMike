package com.mikestf.planetsappmike.ui.list_planets.components

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mikestf.planetsappmike.domain.model.Planets
import com.mikestf.planetsappmike.domain.use_case.GetPlanetListUseCase
import retrofit2.HttpException
import javax.inject.Inject

class PlanetsPagingSource @Inject constructor(
    private val getPlanetListUseCase: GetPlanetListUseCase
) : PagingSource<Int, Planets>() {

    override fun getRefreshKey(state: PagingState<Int, Planets>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planets> {
        return try {
            val page = params.key ?: 1
            val response = getPlanetListUseCase(page)

            LoadResult.Page(
                data = response.data?.map { it } ?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data!!.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(
                e.fillInStackTrace()
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                e.cause!!
            )
        }
    }
}