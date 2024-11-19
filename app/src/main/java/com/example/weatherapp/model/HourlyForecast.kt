package com.example.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class HourlyForecast(
    val time: String,
    val temperature: Float,
    val weatherCode: Int,
    val cloudCover: Int
)
