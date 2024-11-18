package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherData = mutableListOf<String>()

    fun submitList(data: List<String>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val textView = TextView(parent.context).apply {
            textSize = 18f
            setPadding(16, 16, 16, 16)
        }
        return WeatherViewHolder(textView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

    class WeatherViewHolder(private val textView: TextView) :
        RecyclerView.ViewHolder(textView) {
        fun bind(data: String) {
            textView.text = data
        }
    }
}