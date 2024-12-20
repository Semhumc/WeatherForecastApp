package com.example.wheatherapp.data

import com.example.wheatherapp.model.CurrentWheatherApi
import com.example.wheatherapp.model.ForecastWheatherApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): CurrentWheatherApi


    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): ForecastWheatherApi


}
