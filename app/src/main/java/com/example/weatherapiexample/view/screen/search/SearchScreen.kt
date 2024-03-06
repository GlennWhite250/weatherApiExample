package com.example.weatherapiexample.view.screen.search

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.weatherapiexample.view.screen.common.NavigationScreen
import com.example.weatherapiexample.viewmodel.weatherViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: weatherViewModel = hiltViewModel()
) {
    val searchLocation by searchViewModel.searchLocation.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchLocation,
                onTextChange = {
                               searchViewModel.updateSearchLocation(location = it)
                },
                onSearchClicked = {
                    searchViewModel.searchWeatherData(location = it)
                },
                onCloseClicked = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                        navController.popBackStack()
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(
                        bottom = it.calculateBottomPadding(),
                        top = it.calculateTopPadding()
                    )
            ) {
                NavigationScreen(weatherUiState = searchViewModel.weatherUiState)
            }
        }
    )
}
