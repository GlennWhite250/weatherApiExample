package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeather,
    @SerializedName("forecast")
    val futureForecast: FutureForecast,
    @SerializedName("location")
    val currentLocation: CurrentLocation
)