package com.example.weatherapp.model

data class HourlyForecast(
    val time: String,
    val temperature: Float,
    val weatherCode: Int,
    val cloudCover: Int
)
