package com.example.weatherapiexample.viewmodel

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapiexample.model.devicelocation.LocationDetails
import com.example.weatherapiexample.model.repository.Repository
import com.example.weatherapiexample.model.response.current.CurrentWeatherResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class weatherViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private var locationCallback: LocationCallback? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null


    private val _searchLocation = MutableStateFlow("")
    val searchLocation = _searchLocation.asStateFlow()

    private val _weatherData = MutableStateFlow<CurrentWeatherResponse?>(null)
    val weatherData = _weatherData.asStateFlow()

    private val _weatherDeviceData = MutableStateFlow<CurrentWeatherResponse?>(null)
    val weatherDeviceData = _weatherDeviceData.asStateFlow()

    var currentLocation by mutableStateOf<LocationDetails?>(value = null)
        private set

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    fun updateSearchLocation(location: String) {
        _searchLocation.value = location
    }

    fun updateCurrentLocation(location: LocationDetails?) {
        viewModelScope.launch {
            currentLocation = location
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun searchWeatherData(location: String) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState = try {
                _weatherData.value = repository.getSearchedWeather(location = location)
                WeatherUiState.Success(weatherData.value)
            } catch (e: IOException) {
                WeatherUiState.Error(e.message)
            } catch (e: HttpException) {
                WeatherUiState.Error(e.message)
            } catch (e: Exception) {
                WeatherUiState.Error("We could not find this city")
            }

        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
     fun deviceWeatherData(deviceLocation: String) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState = try {
                _weatherDeviceData.value = repository.getCurrentDeviceLocation(deviceLocation = deviceLocation)
                WeatherUiState.Success(weatherDeviceData.value)
            } catch (e: IOException) {
                WeatherUiState.Error(e.message)
            } catch (e: HttpException) {
                WeatherUiState.Error(e.message)
            }catch (e: Exception) {
                WeatherUiState.Error(e.message)
            }

        }
    }


    @SuppressLint("MissingPermission")
     fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(60)).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            fusedLocationProviderClient?.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    @Composable
    fun GetLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    updateCurrentLocation(LocationDetails(lo.latitude, lo.longitude))
                }
            }
        }
    }
}