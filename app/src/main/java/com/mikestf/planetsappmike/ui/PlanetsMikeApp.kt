package com.mikestf.planetsappmike.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.mikestf.planetsappmike.ui.theme.PlanetsAppMikeTheme

/*
* Se asignan las acciones de navegación
*/
@Composable
fun  PlanetsMikeApp() {
    PlanetsAppMikeTheme {
        val navController = rememberNavController()
        val navigateActions = remember(navController) {
            PlanetsMikeActions(navController)
        }

        PlanetsMikeNavGraph(
            navController = navController,
            navigateToRegister = navigateActions.navigateToRegister,
            navigateToListPlanets = navigateActions.navigateToListPlanets,
            navigateToDetail = navigateActions.navigateToDetail
        )
    }
}