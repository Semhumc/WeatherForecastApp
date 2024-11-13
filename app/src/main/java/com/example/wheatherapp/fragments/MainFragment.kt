package com.example.wheatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.adapter.RecyclerViewAdapter
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentMainBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.WeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

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

        // Set up RecyclerView
        recyclerViewAdapter = RecyclerViewAdapter(emptyList())
        binding.MainRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerViewAdapter
        }

        // Observe weather data
        viewModel.weatherData.observe(viewLifecycleOwner) { weatherList ->
            recyclerViewAdapter.updateData(weatherList)
        }

        viewModel.fetchWeatherForCities(cities)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
