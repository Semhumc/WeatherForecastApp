package com.example.wheatherapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.databinding.ItemsBinding
import com.example.wheatherapp.model.CurrentWheatherApi

class RecyclerViewAdapter(
    private var data: List<CurrentWheatherApi>
) : RecyclerView.Adapter<RecyclerViewAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrentWheatherApi) {
            binding.mainCityTextView.text = item.name ?: "Unknown City"
            binding.mainTemperatureTextView.text = "${item.main?.temp ?: "N/A"}°C"

            val description = item.weather?.firstOrNull()?.description ?: "No description available"
            binding.mainDescTextView.text = description

            val condition = item.weather?.firstOrNull()?.description ?: ""
            changeIconAccordingToWeatherCondition(condition)

            val cardViewBgColor = changeBgColorAccordingToWeatherCondition(condition)
            binding.cardView.setCardBackgroundColor(cardViewBgColor)

        }

        private fun changeIconAccordingToWeatherCondition(condition: String) {
            when (condition.lowercase()) {
                "rain", "shower rain","light rain" -> {
                    binding.mainImageView.setImageResource(R.drawable._rainy)

                }

                "thunderstorm" -> {
                    binding.mainImageView.setImageResource(R.drawable._storm)

                }

                "mist" -> {
                    binding.mainImageView.setImageResource(R.drawable._fog)

                }

                "snow" -> {
                    binding.mainImageView.setImageResource(R.drawable._snowy)
                }

                "broken clouds", "scattered clouds", "few clouds","overcast clouds" -> {
                    binding.mainImageView.setImageResource(R.drawable._cloudy)
                }

                "clear sky" -> {
                    binding.mainImageView.setImageResource(R.drawable._sunny)
                }

                else -> {
                    binding.mainImageView.setImageResource(R.drawable._clear)
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<CurrentWheatherApi>) {
        data = newData
        notifyDataSetChanged()
    }
}