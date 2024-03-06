package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FutureForecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)