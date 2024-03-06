package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Astro(
    @SerializedName("is_moon_up")
    val isMoonUp: Int,
    @SerializedName("is_sun_up")
    val isSunUp: Int,
    @SerializedName("moon_illumination")
    val moonIllumination: Int,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("moonrise")
    val moonrise: String,
    @SerializedName("moonset")
    val moonset: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)