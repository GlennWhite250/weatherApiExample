package com.example.weatherapiexample.view.screen.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.weatherapiexample.view.screen.Screen
import com.example.weatherapiexample.view.screen.common.NavigationScreen
import com.example.weatherapiexample.viewmodel.weatherViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(navHostController: NavHostController, homeViewModel: weatherViewModel = hiltViewModel()) {
    val deviceWeatherData by homeViewModel.weatherDeviceData.collectAsStateWithLifecycle()
    val currentLocation = homeViewModel.currentLocation
    homeViewModel.GetLocation()
    homeViewModel.startLocationUpdates()
    homeViewModel.deviceWeatherData(deviceLocation = "${currentLocation?.latitude},${currentLocation?.longitude}")

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navHostController.navigate(Screen.Search.route)
                },
                location = "${deviceWeatherData?.currentLocation?.name}, ${deviceWeatherData?.currentLocation?.region}"
            )
        },
        content = {
            Box(modifier = Modifier
                .padding(
                    bottom = it.calculateBottomPadding(),
                    top = it.calculateTopPadding()
                )
            ) {
                NavigationScreen(weatherUiState = homeViewModel.weatherUiState)
            }
        }
    )
}