package com.example.wheatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wheatherapp.repository.WheatherRepository

class WheatherViewModelFactory (
    private val repository: WheatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel > create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}