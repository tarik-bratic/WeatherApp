package com.example.weatherapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.HourlyForecast
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    vm: WeatherViewModel
) {
    val weatherData = vm.weatherLiveData.observeAsState(emptyList())
    val errorMessage = vm.errorLiveData.observeAsState()
    val latitude = remember { mutableStateOf("") }
    val longitude = remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Weather Information",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Latitude Input
            OutlinedTextField(
                value = latitude.value,
                onValueChange = { latitude.value = it },
                label = { Text("Enter Latitude") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Longitude Input
            OutlinedTextField(
                value = longitude.value,
                onValueChange = { longitude.value = it },
                label = { Text("Enter Longitude") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Fetch Weather Button
            Button(
                onClick = {
                    val lat = latitude.value.toFloatOrNull()
                    val lon = longitude.value.toFloatOrNull()

                    if (lat != null && lon != null) {
                        vm.getWeather(lat, lon)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch Weather")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Error State
            if (!errorMessage.value.isNullOrEmpty()) {
                Text(
                    text = errorMessage.value ?: "Unknown Error",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                // Display weather data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    items(weatherData.value) { forecast ->
                        WeatherItem(forecast = forecast)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherItem(forecast: HourlyForecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = forecast.time,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${forecast.temperature}°C",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${forecast.weatherCode}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${forecast.cloudCover}%",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}