package com.example.wheatherapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.R
import com.example.wheatherapp.activity.MainActivity
import com.example.wheatherapp.adapter.RecyclerViewAdapter
import com.example.wheatherapp.data.WheatherApi
import com.example.wheatherapp.databinding.FragmentMainBinding
import com.example.wheatherapp.repository.WheatherRepository
import com.example.wheatherapp.viewmodel.WeatherViewModel
import com.example.wheatherapp.viewmodel.WheatherViewModelFactory

class MainFragment : Fragment() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels {
        WheatherViewModelFactory(WheatherRepository(WheatherApi.getClient()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

        val navController = NavHostFragment.findNavController(this)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val cities = listOf("Istanbul", "Ankara", "Izmir")
        recyclerViewAdapter = RecyclerViewAdapter(emptyList()) { cityName ->
            when (cityName) {
                "Istanbul" -> findNavController().navigate(R.id.action_mainFragment_to_istanbulFragment)
                "Ankara" -> findNavController().navigate(R.id.action_mainFragment_to_ankaraFragment)
                "Izmir" -> findNavController().navigate(R.id.action_mainFragment_to_izmirFragment)
            }
        }


        binding.mainRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerViewAdapter
        }

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
