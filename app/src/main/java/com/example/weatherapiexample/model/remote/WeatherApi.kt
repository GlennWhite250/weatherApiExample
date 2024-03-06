package com.example.weatherapiexample.model.remote

import com.example.weatherapiexample.model.response.current.CurrentWeatherResponse
import com.example.weatherapiexample.util.Constants.ALERTS
import com.example.weatherapiexample.util.Constants.API_KEY
import com.example.weatherapiexample.util.Constants.AQI
import com.example.weatherapiexample.util.Constants.DAYS
import com.example.weatherapiexample.util.Constants.END_POINT_DEVICE
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(END_POINT_DEVICE)
    suspend fun fetchSearchWeather(
        @Query("key")
        apiKey: String = API_KEY,
        @Query("aqi")
        aqi: String = AQI,
        @Query("q")
        location: String,
        @Query("alerts")
        alerts: String = ALERTS,
        @Query("days")
        days: Int = DAYS
    ): CurrentWeatherResponse

    @GET(END_POINT_DEVICE)
    suspend fun fetchCurrentLocationWeather(
        @Query("key")
        apiKey: String = API_KEY,
        @Query("aqi")
        aqi: String = AQI,
        @Query("q")
        location: String,
        @Query("alerts")
        alerts: String = ALERTS,
        @Query("days")
        days: Int = DAYS
    ): CurrentWeatherResponse
}