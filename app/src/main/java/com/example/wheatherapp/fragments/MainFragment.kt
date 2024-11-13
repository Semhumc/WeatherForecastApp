package com.example.wheatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentMainBinding
import com.example.wheatherapp.databinding.ItemsBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.WeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cities = listOf("Istanbul", "Ankara", "Izmir")


        viewModel.weatherData.observe(viewLifecycleOwner) { weatherList ->
            weatherList.forEach { weather ->
                addCityItem(
                    cityName = weather.name ?: "Unknown City",
                    temperature = weather.main?.temp?.toString() ?: "N/A",
                )
            }
        }

        // API çağrısı yap
        viewModel.fetchWeatherForCities(cities)
    }

    private fun addCityItem(cityName: String, temperature: String) {
        // items.xml'i şişir
        val itemBinding = ItemsBinding.inflate(layoutInflater, binding.mainLinearLayout, false)

        // Dinamik verileri ayarla
        itemBinding.cityTextView.text = cityName
        itemBinding.temperatureTextView.text = "$temperature°C"

        // Şişirilen öğeyi ana layouta ekle
        binding.mainLinearLayout.addView(itemBinding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
