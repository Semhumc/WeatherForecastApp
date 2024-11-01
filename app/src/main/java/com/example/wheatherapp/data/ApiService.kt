package com.example.wheatherapp.data

import com.example.wheatherapp.model.CurrentWheatherApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    fun getCurrentWheather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") ApiKey:String,


    ):Call<CurrentWheatherApi>
}