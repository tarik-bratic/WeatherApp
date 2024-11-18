package com.example.weatherapp.model

data class WeatherResponse(
    val daily: DailyForecastData
)

data class DailyForecastData(
    val time: List<String>, // List of dates
    val temperature_2m_max: List<Float>, // Max temperatures

)