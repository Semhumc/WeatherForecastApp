package com.example.wheatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.model.CurrentWheatherApi
import com.example.wheatherapp.repository.WheatherRepository
import kotlinx.coroutines.launch

class SingleCityWeatherViewModel(private val repository: WheatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<CurrentWheatherApi>()
    val weatherData: LiveData<CurrentWheatherApi> get() = _weatherData

    fun fetchWeatherForCity(city: String) {
        viewModelScope.launch {
            try {
                val data = repository.getWeatherForCity(city)
                _weatherData.postValue(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
