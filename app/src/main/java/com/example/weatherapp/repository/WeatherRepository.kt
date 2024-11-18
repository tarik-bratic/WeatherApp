package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.model.DailyForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val api: WeatherApi) {

    suspend fun fetchWeather(latitude: Float, longitude: Float): WeatherResponse {
        return withContext(Dispatchers.IO) {
            api.getForecast(latitude, longitude)
        }
    }

    fun mapToDailyForecast(response: WeatherResponse): List<DailyForecast> {
        val forecastList = mutableListOf<DailyForecast>()
        for (i in response.daily.time.indices) {
            val forecast = DailyForecast(
                date = response.daily.time[i],
                temperatureMax = response.daily.temperature_2m_max[i]
            )
            forecastList.add(forecast)
        }
        return forecastList
    }
}