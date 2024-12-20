package com.example.wheatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.model.CurrentWheatherApi
import com.example.wheatherapp.repository.WheatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WheatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<List<CurrentWheatherApi>>()
    val weatherData: LiveData<List<CurrentWheatherApi>> get() = _weatherData

    fun fetchWeatherForCities(cities: List<String>) {
        viewModelScope.launch {
            try {
                val data = repository.getWeatherForCities(cities)
                _weatherData.postValue(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
