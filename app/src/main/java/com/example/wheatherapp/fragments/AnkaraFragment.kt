package com.example.wheatherapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wheatherapp.R
import com.example.wheatherapp.adapter.ForecastRecyclerViewAdapter
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentAnkaraBinding
import com.example.wheatherapp.databinding.FragmentIstanbulBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.ForecastViewModel
import com.example.wheatherapp.viewmodel.SingleCityWeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class AnkaraFragment : Fragment() {


    private var _binding : FragmentAnkaraBinding? = null
    private val binding get() = _binding!!

    private val singleCityWeatherViewModel: SingleCityWeatherViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }

    private val forecastViewModel: ForecastViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }

    private lateinit var forecastAdapter: ForecastRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnkaraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        forecastAdapter = ForecastRecyclerViewAdapter(emptyList())
        binding.forecastRecyclerview.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


        singleCityWeatherViewModel.fetchWeatherForCity("Ankara")
        singleCityWeatherViewModel.weatherData.observe(viewLifecycleOwner) { weatherData ->
            weatherData?.let {
                binding.ankaraTemperatureTextView.text = "${it.main?.temp ?: "N/A"} °C"
                binding.ankaraCityTextView.text = it.name ?: "Unknown City"


                val weatherIcon = it.weather.firstOrNull()?.icon
                val iconUrl = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png" // Use @2x for better resolution
                Glide.with(this)
                    .load(iconUrl)
                    .into(binding.imageView)


                val condition = it.weather.firstOrNull()?.description ?: ""
                val backgroundColor = changeImagsAccordingToWeaterCondition(condition)
                binding.root.setBackgroundColor(backgroundColor)
            }
        }


        forecastViewModel.fetchForecastForCity("Ankara")
        forecastViewModel.forecastData.observe(viewLifecycleOwner) { forecastList ->
            forecastList?.let {
                forecastAdapter.updateData(it.take(5))
            }
        }
    }

    private fun changeImagsAccordingToWeaterCondition(condition: String) : Int {
        return when (condition.lowercase()) {
            "rain", "shower rain" -> {
                Color.parseColor("#003E6D")
            }

            "thunderstorm" -> {
                Color.parseColor("#726998")

            }

            "mist" -> {
                Color.parseColor("#08AE88")

            }

            "snow" -> {
                Color.parseColor("#696969")
            }

            "broken clouds", "scattered clouds", "few clouds" -> {
                Color.parseColor("#FFBD3A")
            }

            "clear sky" -> {
                Color.parseColor("#6488EA")
            }

            else -> Color.WHITE

        }
    }
}