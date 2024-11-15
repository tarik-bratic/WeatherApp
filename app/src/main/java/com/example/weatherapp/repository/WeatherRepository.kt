package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.WeatherResponse

class WeatherRepository(private val api: WeatherApi) {
    suspend fun fetchWeather(latitude: Float, longitude: Float): WeatherResponse {
        return api.getForecast(latitude, longitude)
    }
}