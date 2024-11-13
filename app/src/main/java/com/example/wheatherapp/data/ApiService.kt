
package com.example.wheatherapp.data

import com.example.wheatherapp.model.CurrentWheatherApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): CurrentWheatherApi
}
