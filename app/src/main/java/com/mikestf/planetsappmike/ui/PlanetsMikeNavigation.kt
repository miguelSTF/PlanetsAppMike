package com.mikestf.planetsappmike.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/*
* Navegación de pantalla a pantalla
*/
sealed class Screen(val route: String) {
    object Register: Screen("register")
    object ListPlanets: Screen("listPlanets")
    object Detail: Screen("detail?id={id}") {
        fun passId(id: String): String {
            return "detail?id=$id"
        }
    }
}

class PlanetsMikeActions(navController: NavController) {
    val navigateToRegister: () -> Unit = {
        navController.navigate(Screen.Register.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToListPlanets: () -> Unit = {
        navController.navigate(Screen.ListPlanets.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail = { id: String ->
        navController.navigate(Screen.Detail.passId(id)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState =  true
            }
            launchSingleTop =  true
        }
    }
}