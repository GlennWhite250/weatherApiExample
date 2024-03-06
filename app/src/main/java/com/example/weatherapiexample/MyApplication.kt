package com.example.weatherapiexample

import android.app.Application
import com.example.weatherapiexample.model.di.NetworkModule
import com.example.weatherapiexample.model.di.NetworkModuleImpl
import dagger.hilt.android.HiltAndroidApp

class MyApplication: Application() {
    companion object {
        lateinit var networkModule: NetworkModule
    }

    override fun onCreate() {
        super.onCreate()
        networkModule = NetworkModuleImpl(this)
    }
}