package com.example.wheatherapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wheatherapp.R
import com.example.wheatherapp.adapter.ForecastRecyclerViewAdapter
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentIstanbulBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.ForecastViewModel
import com.example.wheatherapp.viewmodel.SingleCityWeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class IstanbulFragment : Fragment() {


    private var _binding: FragmentIstanbulBinding? = null
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
        _binding = FragmentIstanbulBinding.inflate(inflater, container, false)
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


        singleCityWeatherViewModel.fetchWeatherForCity("Istanbul")
        singleCityWeatherViewModel.weatherData.observe(viewLifecycleOwner) { weatherData ->
            weatherData?.let {
                binding.istanbulTemperatureTextView.text = "${it.main?.temp ?: "N/A"} °C"
                binding.istanbulCityTextView.text = it.name ?: "Unknown City"


                val condition = it.weather.firstOrNull()?.description ?: ""
                val backgroundColor = changeBgColorAccordingToWeatherCondition(condition)
                binding.root.setBackgroundColor(backgroundColor)

                changeIconAccordingToWeatherCondition(condition)
            }
        }


        forecastViewModel.fetchForecastForCity("Istanbul")
        forecastViewModel.forecastData.observe(viewLifecycleOwner) { forecastList ->
            forecastList?.let {
                forecastAdapter.updateData(it.take(5))
            }
        }
    }

    private fun changeBgColorAccordingToWeatherCondition(condition: String): Int {
        return when (condition.lowercase()) {
            "rain", "shower rain" -> {
                Color.parseColor("#2D3E73")
            }

            "thunderstorm" -> {
                Color.parseColor("#726998")

            }

            "mist" -> {
                Color.parseColor("#08AE88")

            }

            "snow" -> {
                Color.parseColor("#60AFD7")
            }

            "broken clouds", "scattered clouds", "few clouds" -> {
                Color.parseColor("#FF9D12")
            }

            "clear sky" -> {
                Color.parseColor("#00C0E7")
            }

            else -> Color.WHITE

        }


    }

    private fun changeIconAccordingToWeatherCondition(condition: String) {
        when (condition.lowercase()) {
            "rain", "shower rain" -> {
                binding.istanbulImageView.setImageResource(R.drawable._rainy)
            }

            "thunderstorm" -> {
                binding.istanbulImageView.setImageResource(R.drawable._storm)

            }

            "mist" -> {
                binding.istanbulImageView.setImageResource(R.drawable._fog)

            }

            "snow" -> {
                binding.istanbulImageView.setImageResource(R.drawable._snowy)
            }

            "broken clouds", "scattered clouds", "few clouds" -> {
                binding.istanbulImageView.setImageResource(R.drawable._cloudy)
            }

            "clear sky" -> {
                binding.istanbulImageView.setImageResource(R.drawable._sunny)
            }

            else -> {
                binding.istanbulImageView.setImageResource(R.drawable._clear)
            }

        }


    }
}