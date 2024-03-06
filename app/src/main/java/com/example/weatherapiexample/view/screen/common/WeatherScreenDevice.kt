package com.example.weatherapiexample.view.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.weatherapiexample.R
import com.example.weatherapiexample.model.response.current.AirQuality
import com.example.weatherapiexample.model.response.current.Astro
import com.example.weatherapiexample.model.response.current.CurrentLocation
import com.example.weatherapiexample.model.response.current.CurrentWeather
import com.example.weatherapiexample.model.response.current.CurrentWeatherResponse
import com.example.weatherapiexample.model.response.current.Day
import com.example.weatherapiexample.model.response.current.DeviceCondition
import com.example.weatherapiexample.model.response.current.ForecastDay
import com.example.weatherapiexample.model.response.current.FutureForecast
import com.example.weatherapiexample.model.response.current.Hour
import com.example.weatherapiexample.viewmodel.WeatherUiState

@Composable
fun WeatherScreenDevice(weatherData: CurrentWeatherResponse?) {
    if (weatherData != null) {
        val weatherIcon = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https:" + weatherData.currentWeather.condition.icon)
                .apply(
                    block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.ic_placeholder)
                        error(R.drawable.ic_error_foreground)
                        crossfade(durationMillis = 100)
                    }
                ).build()
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.wrapContentWidth()
            ) {

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Now", color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(5.dp))
                    Row {
                        Column {
                            Text(
                                text = weatherData.currentWeather.tempF.toString() + "\u2109",
                                style = MaterialTheme.typography.headlineLarge,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "High: ${weatherData.futureForecast.forecastDay[0].day.maxtempF}\u2109" + " \u2022 " + "Low: ${weatherData.futureForecast.forecastDay[0].day.mintempF}℉",
                                fontSize = 13.sp)
                        }
                        //Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = weatherIcon,
                            contentDescription = "The icon for the weather",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(100.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = weatherData.currentWeather.condition.text, color = MaterialTheme.colorScheme.primary)
                    Text(text = "Feels like ${weatherData.currentWeather.feelslikeF}℉", maxLines = 1)
                }
            }
            Column(
                modifier = Modifier
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(text = "Current Conditions", color = MaterialTheme.colorScheme.primary)
                }
                Row {
                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "Wind", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = "${weatherData.currentWeather.windMph} mph",
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = weatherData.currentWeather.windDir,
                            modifier = Modifier.padding(5.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "Humidity", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = "${weatherData.currentWeather.humidity}%",
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "UV Index", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = "${weatherData.currentWeather.uv}",
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "Pressure", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = "${weatherData.currentWeather.precipIn}''",
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                }
                Row {
                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "Sunrise", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = weatherData.futureForecast.forecastDay[0].astro.sunrise,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "Sunset", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = weatherData.futureForecast.forecastDay[0].astro.sunset,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "moonrise", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = weatherData.futureForecast.forecastDay[0].astro.moonrise,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = "moonset", modifier = Modifier.padding(5.dp))
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(
                            text = weatherData.futureForecast.forecastDay[0].astro.moonset,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Column {
                        Text(text = "Today's Hourly Conditions:", color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(5.dp))
                        HourlyForecast(items = weatherData.futureForecast.forecastDay[0].hour)
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Column {
                        Text(text = "3-day forecast:", color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(5.dp))
                        ThreeDayForecast(list = weatherData.futureForecast.forecastDay)
                    }
                }
            }
        }
    } else {
        Text(text = "Please look up a city.", color = MaterialTheme.colorScheme.error)
    }
}

@Preview
@Composable
fun WeatherScreenDevicePreview() {
    WeatherScreenDevice(
        weatherData = CurrentWeatherResponse(
            currentWeather = CurrentWeather(
                lastUpdated = "2024-01-17 14:30",
                lastUpdatedEpoch = 1705501800,
                tempC = 2.0,
                tempF = 35.6,
                isDay = 1,
                condition = DeviceCondition(
                    text = "Partly cloudy",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                    code = 1003
                ),
                windMph = 11.9,
                windKph = 19.1,
                windDegree = 20,
                windDir = "NNE",
                pressureMb = 990.0,
                precipIn = 29.23,
                precipMm = 0.0,
                pressureIn = 0.0,
                humidity = 80,
                cloud = 25,
                feelslikeC = -2.1,
                feelslikeF = 28.2,
                visKm = 10.0,
                visMiles = 6.0,
                uv = 1.0,
                gustMph = 12.7,
                gustKph = 20.5,
                airQuality = AirQuality(
                    co = 267.0,
                    no2 = 12.3,
                    o3 = 62.2,
                    so2 = 5.4,
                    pm25 = 6.1,
                    pm10 = 7.0,
                    usEpaIndex = 1,
                    gbDefraIndex = 1
                )
            ),
            currentLocation = CurrentLocation(
                name = "London",
                region = "City of London, Greater London",
                country = "United Kingdom",
                lat = 51.52,
                lon = -0.11,
                tzId = "Europe/London",
                localtimeEpoch = 1705501930,
                localtime = "2024-01-17 14:32"
            ),
            futureForecast = FutureForecast(
                listOf(
                    ForecastDay(
                        date = "2024-01-28",
                        dateEpoch = 1706400000,
                        day = Day(
                            maxtempF = 50.1,
                            maxtempC = 10.0,
                            mintempC = 4.8,
                            mintempF = 40.7,
                            avgtempC = 7.3,
                            avgtempF = 45.2,
                            maxwindKph = 15.8,
                            maxwindMph = 9.8,
                            totalprecipIn = 0.0,
                            totalprecipMm = 0.0,
                            avgvisKm = 10.0,
                            avghumidity = 69,
                            dailyChanceOfRain = 0,
                            dailyChanceOfSnow = 0,
                            dailyWillItRain = 0,
                            dailyWillItSnow = 0,
                            dayCondition = DeviceCondition(
                                text = "Partly Cloudy ",
                                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                                code = 1003
                            ),
                            uv = 1.0,
                            avgvisMiles = 6.0,
                            totalsnowCm = 0.0
                        ),
                        astro = Astro(
                            sunrise = "07:46 AM",
                            sunset = "04:42 PM",
                            moonrise = "07:40 PM",
                            moonset = "09:13 AM",
                            moonPhase = "Waning Gibbous",
                            moonIllumination = 95,
                            isMoonUp = 1,
                            isSunUp = 0
                        ),
                        hour = listOf(
                            Hour(
                                timeEpoch = 1706400000,
                                time = "2024-01-28 00:00",
                                tempC = 5.9,
                                tempF = 42.6,
                                isDay = 0,
                                condition = DeviceCondition(
                                    text = "Partly Cloudy ",
                                    icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                                    code = 1003
                                ),
                                windMph = 7.2,
                                windKph = 11.5,
                                windDegree = 181,
                                windDir = "S",
                                pressureMb = 1028.0,
                                pressureIn = 30.36,
                                precipMm = 0.0,
                                precipIn = 0.0,
                                snowCm = 0.0,
                                humidity = 68,
                                cloud = 48,
                                feelslikeF = 38.1,
                                feelslikeC = 3.4,
                                windchillC = 3.4,
                                windchillF = 38.1,
                                heatindexC = 5.9,
                                heatindexF = 42.6,
                                dewpointC = 0.4,
                                dewpointF = 32.8,
                                willItRain = 0,
                                willItSnow = 0,
                                chanceOfSnow = 0,
                                chanceOfRain = 0,
                                visKm = 10.0,
                                visMiles = 6.0,
                                gustMph = 11.5,
                                gustKph = 18.4,
                                uv = 1.0
                            ),
                            Hour(
                                timeEpoch = 1706403600,
                                time = "2024-01-28 01:00",
                                tempC = 5.9,
                                tempF = 42.6,
                                isDay = 0,
                                condition = DeviceCondition(
                                    text = "Partly Cloudy ",
                                    icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                                    code = 1003
                                ),
                                windMph = 7.2,
                                windKph = 11.5,
                                windDegree = 181,
                                windDir = "S",
                                pressureMb = 1028.0,
                                pressureIn = 30.36,
                                precipMm = 0.0,
                                precipIn = 0.0,
                                snowCm = 0.0,
                                humidity = 68,
                                cloud = 48,
                                feelslikeF = 38.1,
                                feelslikeC = 3.4,
                                windchillC = 3.4,
                                windchillF = 38.1,
                                heatindexC = 5.9,
                                heatindexF = 42.6,
                                dewpointC = 0.4,
                                dewpointF = 32.8,
                                willItRain = 0,
                                willItSnow = 0,
                                chanceOfSnow = 0,
                                chanceOfRain = 0,
                                visKm = 10.0,
                                visMiles = 6.0,
                                gustMph = 11.5,
                                gustKph = 18.4,
                                uv = 1.0
                            ),
                            Hour(
                                timeEpoch = 1706403600,
                                time = "2024-01-28 02:00",
                                tempC = 5.9,
                                tempF = 42.6,
                                isDay = 0,
                                condition = DeviceCondition(
                                    text = "Partly Cloudy ",
                                    icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                                    code = 1003
                                ),
                                windMph = 7.2,
                                windKph = 11.5,
                                windDegree = 181,
                                windDir = "S",
                                pressureMb = 1028.0,
                                pressureIn = 30.36,
                                precipMm = 0.0,
                                precipIn = 0.0,
                                snowCm = 0.0,
                                humidity = 68,
                                cloud = 48,
                                feelslikeF = 38.1,
                                feelslikeC = 3.4,
                                windchillC = 3.4,
                                windchillF = 38.1,
                                heatindexC = 5.9,
                                heatindexF = 42.6,
                                dewpointC = 0.4,
                                dewpointF = 32.8,
                                willItRain = 0,
                                willItSnow = 0,
                                chanceOfSnow = 0,
                                chanceOfRain = 0,
                                visKm = 10.0,
                                visMiles = 6.0,
                                gustMph = 11.5,
                                gustKph = 18.4,
                                uv = 1.0
                            ),
                            Hour(
                                timeEpoch = 1706403600,
                                time = "2024-01-28 03:00",
                                tempC = 5.9,
                                tempF = 42.6,
                                isDay = 0,
                                condition = DeviceCondition(
                                    text = "Partly Cloudy ",
                                    icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                                    code = 1003
                                ),
                                windMph = 7.2,
                                windKph = 11.5,
                                windDegree = 181,
                                windDir = "S",
                                pressureMb = 1028.0,
                                pressureIn = 30.36,
                                precipMm = 0.0,
                                precipIn = 0.0,
                                snowCm = 0.0,
                                humidity = 68,
                                cloud = 48,
                                feelslikeF = 38.1,
                                feelslikeC = 3.4,
                                windchillC = 3.4,
                                windchillF = 38.1,
                                heatindexC = 5.9,
                                heatindexF = 42.6,
                                dewpointC = 0.4,
                                dewpointF = 32.8,
                                willItRain = 0,
                                willItSnow = 0,
                                chanceOfSnow = 0,
                                chanceOfRain = 0,
                                visKm = 10.0,
                                visMiles = 6.0,
                                gustMph = 11.5,
                                gustKph = 18.4,
                                uv = 1.0
                            )
                        )
                    )
                )
            )
        )
    )
}