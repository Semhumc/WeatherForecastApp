package com.example.wheatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.adapter.ForecastRecyclerViewAdapter
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentIzmirBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.ForecastViewModel
import com.example.wheatherapp.viewmodel.SingleCityWeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class IzmirFragment : Fragment() {

    private var _binding: FragmentIzmirBinding? = null
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
        _binding = FragmentIzmirBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView setup
        forecastAdapter = ForecastRecyclerViewAdapter(emptyList())
        binding.forecastRecyclerview.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Fetch current weather data for Izmir
        singleCityWeatherViewModel.fetchWeatherForCity("Izmir")
        singleCityWeatherViewModel.weatherData.observe(viewLifecycleOwner) { weatherData ->
            weatherData?.let {
                binding.izmirTemperatureTextView.text = "${it.main?.temp ?: "N/A"} °C"
                binding.izmirCityTextView.text = it.name ?: "Unknown City"
            }
        }

        // Fetch forecast data for Izmir
        forecastViewModel.fetchForecastForCity("Izmir")
        forecastViewModel.forecastData.observe(viewLifecycleOwner) { forecastList ->
            forecastList?.let {
                forecastAdapter.updateData(it.take(5))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
