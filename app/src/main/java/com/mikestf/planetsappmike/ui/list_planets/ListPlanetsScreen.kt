package com.mikestf.planetsappmike.ui.list_planets

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mikestf.planetsappmike.R
import com.mikestf.planetsappmike.ui.list_planets.components.PlanetItem
import com.mikestf.planetsappmike.ui.list_planets.view_model.ListPlanetsViewModel
import com.mikestf.planetsappmike.ui.theme.PurplePlanets
import com.mikestf.planetsappmike.utils.MESSAGE_ERROR

@Composable
fun ListPlanetsScreen (
    onItemClicked: (String) -> Unit,
    viewModel: ListPlanetsViewModel = hiltViewModel()
) {
    val response = viewModel.planetResponse.collectAsLazyPagingItems()

    Scaffold (
        topBar = { TopBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding))
        {

            items(response.itemCount) { planet ->
                PlanetItem(item = response[planet]!!, onItemClicked = { onItemClicked(it) })
            }
            response.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    is LoadState.Error -> {
                        val e = response.loadState.refresh as LoadState.Error
                        Log.i("LOG_ERROR", e.toString())
                        item {
                            Text(text = MESSAGE_ERROR)
                        }
                    }
                    is LoadState.NotLoading -> { }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar (
        colors = TopAppBarDefaults.topAppBarColors(containerColor = PurplePlanets),
        title = {
            Text(
                text = stringResource(id = R.string.planets),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = modifier.wrapContentSize(Alignment.Center)
            )
        }
    )
}