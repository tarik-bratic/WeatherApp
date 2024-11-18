package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.model.HourlyForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val api: WeatherApi) {

    suspend fun fetchWeather(latitude: Float, longitude: Float): WeatherResponse {
        return withContext(Dispatchers.IO) {
            api.getForecast(latitude, longitude)
        }
    }

    fun mapToHourlyForecast(response: WeatherResponse): List<HourlyForecast> {
        val forecastList = mutableListOf<HourlyForecast>()
        for (i in response.hourly.time.indices) {
            val forecast = HourlyForecast(
                time = response.hourly.time[i],
                temperature = response.hourly.temperature_2m[i],
                weatherCode = response.hourly.weather_code[i],
                cloudCover = response.hourly.cloud_cover[i]
            )
            forecastList.add(forecast)
        }
        return forecastList
    }
}