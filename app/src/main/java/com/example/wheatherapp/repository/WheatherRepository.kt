package com.example.wheatherapp.repository

import com.example.wheatherapp.data.ApiService

class WheatherRepository(val api:ApiService) {
    fun getCurrentWheather(lat : Double, lng: Double, unit:String) =
        api.getCurrentWheather(lat,lng,unit,"1c70d7762ae79b400715093ac127fed4")
}