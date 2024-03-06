package com.example.weatherapiexample.model.response.current


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceCondition(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
)