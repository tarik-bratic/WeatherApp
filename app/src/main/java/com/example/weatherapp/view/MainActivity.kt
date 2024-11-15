package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.api.WeatherApiService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(WeatherRepository(WeatherApiService.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Set up button click listener
        binding.btnFetchWeather.setOnClickListener {
            val latitude = binding.etLatitude.text.toString().toFloatOrNull()
            val longitude = binding.etLongitude.text.toString().toFloatOrNull()

            if (latitude != null && longitude != null) {
                viewModel.getWeather(latitude, longitude)
            } else {
                binding.tvError.text = "Invalid latitude or longitude"
                binding.tvError.visibility = View.VISIBLE
            }
        }

        // Observe ViewModel
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = WeatherAdapter()
    }

    private fun observeViewModel() {
        viewModel.weatherLiveData.observe(this) { forecast ->
            (binding.recyclerView.adapter as WeatherAdapter).submitList(forecast)
        }

        viewModel.errorLiveData.observe(this) { error ->
            binding.tvError.text = error
            binding.tvError.visibility = View.VISIBLE
        }
    }
}