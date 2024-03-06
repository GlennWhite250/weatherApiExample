package com.example.weatherapiexample.viewmodel

import com.example.weatherapiexample.model.response.current.CurrentWeatherResponse

sealed interface WeatherUiState {
    data class Success(val currentWeatherResponse: CurrentWeatherResponse?) : WeatherUiState
    data class Error(val errorMessage: String?) : WeatherUiState
    object Loading : WeatherUiState
}