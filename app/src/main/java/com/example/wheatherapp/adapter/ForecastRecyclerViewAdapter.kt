package com.example.wheatherapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.databinding.ForecastItemsBinding
import com.example.wheatherapp.extension.DateFormats
import com.example.wheatherapp.extension.convertDateToWeekNameYear
import com.example.wheatherapp.model.ForecastWheatherApi


class ForecastRecyclerViewAdapter(
    private var data: List<ForecastWheatherApi.Item0>
) : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(private val binding: ForecastItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastWheatherApi.Item0) {

            val formattedDate = item.dtTxt?.convertDateToWeekNameYear(DateFormats.DEFAULT_FORMAT.format) ?: "N/A"

            binding.dateTextView.text = formattedDate
            binding.temperatureTextView.text = item.main?.temp?.let {
                String.format("%.1f°C", it)
            } ?: "N/A°C"
            val description = item.weather?.firstOrNull()?.description ?: "No description available"
            binding.descTextView.text = description

            val condition = item.weather?.firstOrNull()?.description ?: ""
            changeIconAccordingToWeatherCondition(condition)

        }

        private fun changeIconAccordingToWeatherCondition(condition: String) {
            when (condition.lowercase()) {
                "rain", "shower rain","light rain" -> {
                    binding.forcastImageView.setImageResource(R.drawable._rainy)

                }

                "thunderstorm" -> {
                    binding.forcastImageView.setImageResource(R.drawable._storm)

                }

                "mist" -> {
                    binding.forcastImageView.setImageResource(R.drawable._fog)

                }

                "snow" -> {
                    binding.forcastImageView.setImageResource(R.drawable._snowy)
                }

                "broken clouds", "scattered clouds", "few clouds","overcast clouds" -> {
                    binding.forcastImageView.setImageResource(R.drawable._cloudy)
                }

                "clear sky" -> {
                    binding.forcastImageView.setImageResource(R.drawable._sunny)
                }

                else -> {
                    binding.forcastImageView.setImageResource(R.drawable._clear)
                }

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ForecastItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<ForecastWheatherApi.Item0>) {
        data = newData
        notifyDataSetChanged()
    }


}
