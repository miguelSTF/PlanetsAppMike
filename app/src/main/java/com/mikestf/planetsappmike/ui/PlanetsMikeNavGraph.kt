package com.mikestf.planetsappmike.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mikestf.planetsappmike.ui.detail_planet.DetailPlanetScreen
import com.mikestf.planetsappmike.ui.list_planets.ListPlanetsScreen
import com.mikestf.planetsappmike.ui.register_user.RegisterUserScreen

/*
* Eventos para la navegación
*/
@Composable
fun  PlanetsMikeNavGraph (
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToListPlanets: () -> Unit,
    navigateToDetail: (String) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Register.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Register.route) {
            RegisterUserScreen(
                onButtonClicked = { navigateToListPlanets() }
            )
        }
        composable(route = Screen.ListPlanets.route) {
            BackHandler(true) { }

            ListPlanetsScreen(
                onItemClicked = { navigateToDetail(it) }
            )
        }
        composable(route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) {
            DetailPlanetScreen(
                upPress = { navigateToListPlanets() }
            )
        }
    }
}