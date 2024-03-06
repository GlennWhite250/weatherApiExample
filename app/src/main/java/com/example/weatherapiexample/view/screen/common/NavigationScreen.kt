package com.example.weatherapiexample.view.screen.common

import androidx.compose.runtime.Composable
import com.example.weatherapiexample.viewmodel.WeatherUiState

@Composable
fun NavigationScreen(weatherUiState: WeatherUiState) {
    when(weatherUiState) {
        is WeatherUiState.Loading -> LoadingScreen()
        is WeatherUiState.Error -> ErrorScreen(weatherUiState.errorMessage)
        is WeatherUiState.Success -> WeatherScreenDevice(weatherData = weatherUiState.currentWeatherResponse)
    }
}