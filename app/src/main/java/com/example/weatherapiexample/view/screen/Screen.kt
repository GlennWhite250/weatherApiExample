package com.example.weatherapiexample.view.screen

import com.example.weatherapiexample.util.Constants.HOME_SCREEN
import com.example.weatherapiexample.util.Constants.SEARCH_SCREEN

sealed class Screen(val route: String) {
    data object Search: Screen(SEARCH_SCREEN)
    data object HOME: Screen(HOME_SCREEN)
}