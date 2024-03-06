package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    @SerializedName("astro")
    val astro: Astro,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val day: Day,
    @SerializedName("hour")
    val hour: List<Hour>
)