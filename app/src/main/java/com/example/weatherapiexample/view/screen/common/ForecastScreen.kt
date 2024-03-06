package com.example.weatherapiexample.view.screen.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.weatherapiexample.model.response.current.Astro
import com.example.weatherapiexample.model.response.current.Day
import com.example.weatherapiexample.model.response.current.DeviceCondition
import com.example.weatherapiexample.model.response.current.ForecastDay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ThreeDayForecast(list: List<ForecastDay>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        items(list) { day: ForecastDay ->
            DayCard(dayInfo = day)
        }
    }
}

@Composable
fun DayCard(dayInfo: ForecastDay) {
    val weatherIcon = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = "https:${dayInfo.day.dayCondition.icon}")
            .apply(
                block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_placeholder)
                    error(R.drawable.ic_error_foreground)
                    crossfade(durationMillis = 100)
                }
            ).build()
    )
    val localTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dayOfTheWeek = LocalDate.parse(dayInfo.date, localTimeFormat)
    val currentDay = LocalDate.now()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = if (dayOfTheWeek.dayOfWeek.name == currentDay.dayOfWeek.name) {
                    "Today"
                } else {
                    dayOfTheWeek.dayOfWeek.name
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = weatherIcon,
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${dayInfo.day.maxtempF}℉/${dayInfo.day.mintempF}℉", maxLines = 1)


        }
    }
}


@Preview
@Composable
fun DayCardPreview() {
    DayCard(
        dayInfo = ForecastDay(
            date = "2024-02-01",
            dateEpoch = 1706745600,
            day = Day(
                maxtempC = 8.2,
                maxtempF = 46.8,
                mintempC = 4.5,
                mintempF = 40.1,
                avgtempC = 6.6,
                avgtempF = 43.9,
                maxwindMph = 11.0,
                maxwindKph = 17.6,
                totalprecipMm = 0.16,
                totalprecipIn = 0.01,
                totalsnowCm = 0.0,
                avgvisKm = 10.0,
                avgvisMiles = 6.0,
                avghumidity = 70,
                dailyWillItRain = 0,
                dailyChanceOfRain = 62,
                dailyWillItSnow = 0,
                dailyChanceOfSnow = 0,
                dayCondition = DeviceCondition(
                    text = "Patchy rain nearby",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/176.png",
                    code = 1063
                ),
                uv = 1.0
            ),
            astro = Astro(
                sunrise = "07:40 AM",
                sunset = "04:49 PM",
                moonrise = "No moonrise",
                moonset = "09:54 AM",
                moonPhase = "Waning Gibbous",
                moonIllumination = 69,
                isMoonUp = 1,
                isSunUp = 0
            ),
            hour = listOf()
        )
    )
}