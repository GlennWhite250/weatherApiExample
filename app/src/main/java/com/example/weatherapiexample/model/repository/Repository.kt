package com.example.weatherapiexample.model.repository

import com.example.weatherapiexample.model.remote.WeatherApi
import com.example.weatherapiexample.model.response.current.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getSearchedWeather(location: String): CurrentWeatherResponse {
        return withContext(Dispatchers.IO) {
            weatherApi.fetchSearchWeather(location = location)
        }
    }

    suspend fun getCurrentDeviceLocation(deviceLocation: String): CurrentWeatherResponse {
        return withContext(Dispatchers.IO){
            weatherApi.fetchCurrentLocationWeather(location = deviceLocation)
        }
    }
}