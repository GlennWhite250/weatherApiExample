package com.example.weatherapiexample.view.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapiexample.MyApplication
import com.example.weatherapiexample.view.screen.Screen
import com.example.weatherapiexample.view.screen.home.HomeScreen
import com.example.weatherapiexample.view.screen.search.SearchScreen
import com.example.weatherapiexample.viewmodel.WeatherViewModel
import com.example.weatherapiexample.viewmodel.viewModelFactory

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    val viewModel = viewModel<WeatherViewModel>(
        factory = viewModelFactory {
            WeatherViewModel(MyApplication.networkModule.repo)
        }
    )
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController, searchViewModel = viewModel)
        }

        composable(route = Screen.HOME.route) {
            HomeScreen(navHostController = navController, homeViewModel = viewModel)
        }
    }
}