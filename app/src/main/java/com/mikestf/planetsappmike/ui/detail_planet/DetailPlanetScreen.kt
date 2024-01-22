package com.mikestf.planetsappmike.ui.detail_planet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.mikestf.planetsappmike.R
import com.mikestf.planetsappmike.domain.model.DetailPlanet
import com.mikestf.planetsappmike.ui.detail_planet.components.DetailProperty
import com.mikestf.planetsappmike.ui.detail_planet.components.PlanetImageSetup
import com.mikestf.planetsappmike.ui.detail_planet.view_model.DetailPlanetViewModel
import com.mikestf.planetsappmike.ui.theme.PurplePlanets
import java.util.Locale

@Composable
fun DetailPlanetScreen(
    viewModel: DetailPlanetViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    val state = viewModel.state
    DetailContent(
        detailPlanet = state.detailPlanet,
        upPress = upPress
    )
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    detailPlanet: DetailPlanet?,
    upPress: () -> Unit
) {
    Scaffold(
        topBar = { MapTopBar(upPress = upPress) }
    ) {
        Box(
            modifier
                .fillMaxSize()
                .padding(it)) {
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                Body(detailPlanet = detailPlanet)
            }
            BackScreen(upPress)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapTopBar(
    modifier: Modifier = Modifier,
    upPress: () -> Unit
) {
    TopAppBar (
        colors = TopAppBarDefaults.topAppBarColors(containerColor = PurplePlanets),
        title = {
            Text(
                text = stringResource(id = R.string.detail_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = upPress) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.top_bar_description)
                )
            }
        }
    )
}

@Composable
private fun Body(detailPlanet: DetailPlanet?, isLoading: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        if (isLoading) FullScreenLoading()

        PlanetImageSetup()

        DetailProperty(
            label = stringResource(R.string.name_user),
            value = detailPlanet?.name.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() },
            imageVector = Icons.Outlined.ThumbUp)
        DetailProperty(
            label = stringResource(R.string.terrain),
            value = detailPlanet?.terrain.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() },
            imageVector = Icons.Outlined.Home)
        DetailProperty(
            label = stringResource(R.string.population),
            value = detailPlanet?.population.toString(),
            imageVector = Icons.Outlined.Person )
        DetailProperty(
            label = stringResource(R.string.climate),
            value = detailPlanet?.climate.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() },
            imageVector = Icons.Outlined.Star )
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BackScreen(upPress: () -> Unit) {
    BackHandler(
        enabled = true,
        onBack = upPress
    )
}