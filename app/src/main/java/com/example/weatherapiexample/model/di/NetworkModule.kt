package com.example.weatherapiexample.model.di

import android.content.Context
import com.example.weatherapiexample.model.remote.WeatherApi
import com.example.weatherapiexample.model.repository.Repository
import com.example.weatherapiexample.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
interface NetworkModule {
    val weatherApi: WeatherApi
    val repo: Repository
}

class NetworkModuleImpl(): NetworkModule {
    override val weatherApi: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    override val repo: Repository by lazy {
        Repository(weatherApi = weatherApi)
    }

}
