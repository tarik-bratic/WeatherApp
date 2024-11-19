package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.HourlyForecast
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.NetworkUtils
import kotlinx.coroutines.launch

class WeatherViewModel(
    application: Application,
    private val repository: WeatherRepository
) : AndroidViewModel(application) {
    private val _weatherLiveData = MutableLiveData<List<HourlyForecast>>()
    val weatherLiveData: LiveData<List<HourlyForecast>> get() = _weatherLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> get() = _errorLiveData

    fun getWeather(latitude: Float, longitude: Float) {
        if (NetworkUtils.isOnline(getApplication())) {
            android.util.Log.d("NetworkCheck", "Device is online.")

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
        } else {
            android.util.Log.d("NetworkCheck", "Device is offline.")

            _errorLiveData.value = "No internet connection. Please try again later."
        }
    }
}