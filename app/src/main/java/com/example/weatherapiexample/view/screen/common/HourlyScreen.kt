package com.example.weatherapiexample.view.screen.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.weatherapiexample.R
import com.example.weatherapiexample.model.response.current.DeviceCondition
import com.example.weatherapiexample.model.response.current.Hour
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyForecast(items: List<Hour>) {
    Card {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(items) { item: Hour ->
                HourlyCardItem(hourData = item)
            }
        }
    }
}

@Composable
fun HourlyCardItem(hourData: Hour) {
    val localTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val date = LocalDateTime.parse(hourData.time, localTimeFormat).toLocalTime()
    val current = LocalDateTime.now().format(localTimeFormat)
    val test = LocalDateTime.parse(current, localTimeFormat).toLocalTime()

    if (date.hour >= test.hour) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {

            Text(text = "${hourData.tempF}℉")
            Spacer(modifier = Modifier.height(15.dp))
            val weatherIcon = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = "https:${hourData.condition.icon}")
                    .apply(
                        block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_error_foreground)
                            crossfade(durationMillis = 100)
                        }
                    ).build()
            )
            Image(
                painter = weatherIcon,
                contentDescription = "weather icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = if (date.hour == test.hour){
                    "Now"
                } else {
                    when (date.toString()) {
                        "00:00" -> "Midnight"
                        "01:00" -> "01:00am"
                        "02:00" -> "02:00am"
                        "03:00" -> "03:00am"
                        "04:00" -> "04:00am"
                        "05:00" -> "05:00am"
                        "06:00" -> "06:00am"
                        "07:00" -> "07:00am"
                        "08:00" -> "08:00am"
                        "09:00" -> "09:00am"
                        "10:00" -> "10:00am"
                        "11:00" -> "11:00am"
                        "12:00" -> "Noon"
                        "13:00" -> "01:00pm"
                        "14:00" -> "02:00pm"
                        "15:00" -> "03:00pm"
                        "16:00" -> "04:00pm"
                        "17:00" -> "05:00pm"
                        "18:00" -> "06:00pm"
                        "19:00" -> "07:00pm"
                        "20:00" -> "08:00pm"
                        "21:00" -> "09:00pm"
                        "22:00" -> "10:00pm"
                        "23:00" -> "11:00pm"
                        else -> "Blank"
                    }
                }
            )

        }
    }
}


@Preview
@Composable
fun HourlyForecastPreview() {
    HourlyForecast(
        items = listOf(
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
                tempF = 40.0,
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
            ),
            Hour(
                timeEpoch = 1706403600,
                time = "2024-01-28 04:00",
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
                time = "2024-01-28 05:00",
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
}