package com.example.wheatherapp.repository

import com.example.wheatherapp.data.ApiService

class WheatherRepository(val api:ApiService) {
    fun getCurrentWheather(lat : Double, lon: Double, unit:String) =
        api.getCurrentWheather(lat,lon,unit,"6a2fc9d1af1c443216cb234d40edb22c")
}