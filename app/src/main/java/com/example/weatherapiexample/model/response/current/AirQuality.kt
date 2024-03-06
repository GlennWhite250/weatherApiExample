package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQuality(
    @SerializedName("co")
    val co: Double,
    @SerializedName("gb-defra-index")
    val gbDefraIndex: Int,
    @SerializedName("no2")
    val no2: Double,
    @SerializedName("o3")
    val o3: Double,
    @SerializedName("pm10")
    val pm10: Double,
    @SerializedName("pm2_5")
    val pm25: Double,
    @SerializedName("so2")
    val so2: Double,
    @SerializedName("us-epa-index")
    val usEpaIndex: Int
)