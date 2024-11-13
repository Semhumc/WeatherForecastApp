package com.example.wheatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wheatherapp.R
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.SingleCityWeatherViewModel
import com.example.wheatherapp.viewmodel.WeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class IstanbulFragment : Fragment() {


    private val viewModel: SingleCityWeatherViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_istanbul, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val temperatureTextView: TextView = view.findViewById(R.id.istanbulTemperatureTextView)
        val cityTextView: TextView = view.findViewById(R.id.istanbulCityTextView)


        viewModel.fetchWeatherForCity("istanbul")

        viewModel.weatherData.observe(viewLifecycleOwner) { weatherData ->
            weatherData?.let {
                temperatureTextView.text = "${it.main?.temp ?: "N/A"} °C"
                cityTextView.text = it.name

            }
        }
    }
}