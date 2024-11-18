package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude") lat: Float,
        @Query("longitude") lon: Float,
        @Query("hourly") hourly: String = "temperature_2m,weather_code,cloud_cover",
    ): WeatherResponse
}