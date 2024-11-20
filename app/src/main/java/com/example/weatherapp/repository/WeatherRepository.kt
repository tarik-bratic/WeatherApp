package com.example.weatherapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.model.HourlyForecast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val api: WeatherApi,
    context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

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

    fun saveHourlyForecast(forecast: List<HourlyForecast>) {
        val json = gson.toJson(forecast)
        sharedPreferences.edit().putString("HourlyForecast", json).apply()
    }

    fun loadHourlyForecast(): List<HourlyForecast> {
        val json = sharedPreferences.getString("HourlyForecast", null)
        return if (json != null) {
            val type = object : TypeToken<List<HourlyForecast>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}