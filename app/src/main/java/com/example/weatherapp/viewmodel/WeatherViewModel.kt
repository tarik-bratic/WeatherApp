package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.DailyForecast
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherLiveData = MutableLiveData<List<DailyForecast>>()
    val weatherLiveData: LiveData<List<DailyForecast>> get() = _weatherLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> get() = _errorLiveData

    fun getWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            try {
                // Fetch the weather response
                val response = repository.fetchWeather(latitude, longitude)

                // Map the API response to DailyForecast objects
                val dailyForecastList = repository.mapToDailyForecast(response)

                if (dailyForecastList.isEmpty()) {
                    _errorLiveData.value = "No data available for this location."
                } else {
                    _weatherLiveData.value = dailyForecastList
                    _errorLiveData.value = null // Clear previous errors
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error fetching weather: ${e.message}"
            }
        }
    }
}