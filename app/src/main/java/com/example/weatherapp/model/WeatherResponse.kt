package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("hourly") val hourly: HourlyForecastData
)

data class HourlyForecastData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature_2m: List<Float>,
    @SerializedName("weather_code") val weather_code: List<Int>,
    @SerializedName("cloud_cover") val cloud_cover: List<Int>
)