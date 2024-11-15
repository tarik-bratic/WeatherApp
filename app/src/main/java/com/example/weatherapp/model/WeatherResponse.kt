package com.example.weatherapp.model

data class WeatherResponse(
    val daily: List<DayForecast>
)

data class DayForecast(
    val date: String,
    val temperature: Float,
    val cloudCoverage: String
)