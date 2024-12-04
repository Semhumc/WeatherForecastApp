package com.example.wheatherapp.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.model.ForecastWheatherApi
import com.example.wheatherapp.repository.WheatherRepository
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: WheatherRepository) : ViewModel() {

    private val _forecastData = MutableLiveData<List<ForecastWheatherApi.Item0>>()
    val forecastData: LiveData<List<ForecastWheatherApi.Item0>> get() = _forecastData

    fun fetchForecastForCity(city: String) {
        viewModelScope.launch {
            try {
                val forecast = repository.getWeatherForecast(city)
                val dailyForecast = forecast.list?.filterNotNull()?.filterIndexed { index, _ ->
                    index % 8 == 0
                } ?: emptyList()

                _forecastData.postValue(dailyForecast)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}