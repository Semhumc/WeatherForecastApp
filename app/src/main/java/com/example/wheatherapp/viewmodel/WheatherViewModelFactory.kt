package com.example.wheatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wheatherapp.repository.WheatherRepository

class WheatherViewModelFactory(
    private val repository: WheatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> {
                WeatherViewModel(repository) as T
            }


            modelClass.isAssignableFrom(SingleCityWeatherViewModel::class.java) -> {
                SingleCityWeatherViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
