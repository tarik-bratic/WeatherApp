package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.HourlyForecast
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherLiveData = MutableLiveData<List<HourlyForecast>>()
    val weatherLiveData: LiveData<List<HourlyForecast>> get() = _weatherLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> get() = _errorLiveData

    fun getWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeather(latitude, longitude)

                val hourlyForecastList = repository.mapToHourlyForecast(response)

                if (hourlyForecastList.isEmpty()) {
                    _errorLiveData.value = "No data available for this location."
                } else {
                    _weatherLiveData.value = hourlyForecastList
                    _errorLiveData.value = null // Clear previous errors
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error fetching weather: ${e.message}"
            }
        }
    }
}