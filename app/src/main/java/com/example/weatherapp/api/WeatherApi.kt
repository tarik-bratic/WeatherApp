package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude") lat: Float,
        @Query("longitude") lon: Float,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min,cloud_cover",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}