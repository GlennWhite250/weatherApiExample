package com.example.weatherapiexample.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapiexample.view.screen.Screen
import com.example.weatherapiexample.view.screen.home.HomeScreen
import com.example.weatherapiexample.view.screen.search.SearchScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Screen.HOME.route) {
            HomeScreen(navHostController = navController)
        }
    }
}