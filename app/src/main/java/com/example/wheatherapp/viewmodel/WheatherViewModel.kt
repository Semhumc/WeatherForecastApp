package com.example.wheatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wheatherapp.data.ApiService
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.repository.WheatherRepository

class WheatherViewModel(val repository : WheatherRepository):ViewModel() {
    constructor() : this(WheatherRepository(WheatherApi().getClient().create(ApiService::class.java)))

    fun loadCurrentWeather(lat: Double, lng: Double, unit: String) =
        repository.getCurrentWheather(lat, lng, unit)

}