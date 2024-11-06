package com.example.wheatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheatherapp.model.CurrentWheatherApi
import com.example.wheatherapp.repository.WheatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val repository: WheatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<CurrentWheatherApi>()
    val weatherData: LiveData< CurrentWheatherApi> get() = _weatherData

    fun loadCurrentWeather(lat: Double, lon: Double, unit: String) {

        repository.getCurrentWheather(lat, lon, unit).enqueue(object : Callback< CurrentWheatherApi> {
            override fun onResponse(
                call: Call< CurrentWheatherApi>,
                response: Response< CurrentWheatherApi>
            ) {
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                }
            }

            override fun onFailure(call: Call< CurrentWheatherApi>, t: Throwable) {

                _weatherData.value = null
            }
        })
    }
}
