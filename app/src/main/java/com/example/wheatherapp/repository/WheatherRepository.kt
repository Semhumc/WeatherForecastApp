package com.example.wheatherapp.repository

import com.example.wheatherapp.data.ApiService
import com.example.wheatherapp.model.CurrentWheatherApi
import com.example.wheatherapp.model.ForecastWheatherApi

class WheatherRepository(private val api: ApiService) {
    val apiKey = "6a2fc9d1af1c443216cb234d40edb22c"

    suspend fun getWeatherForCities(cities: List<String>): List<CurrentWheatherApi> {

        return cities.map { city ->
            api.getCurrentWeather(city, "metric", apiKey)
        }
    }

    suspend fun getWeatherForCity(city: String): CurrentWheatherApi {
        return api.getCurrentWeather(city, "metric", apiKey)
    }

    suspend fun getWeatherForecast(city: String): ForecastWheatherApi {
        return api.getForecast(city, "metric", apiKey)
    }
}
