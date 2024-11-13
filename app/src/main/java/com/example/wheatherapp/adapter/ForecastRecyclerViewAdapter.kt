package com.example.wheatherapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.databinding.ItemsBinding
import com.example.wheatherapp.model.ForecastWheatherApi

class ForecastRecyclerViewAdapter (private var forecastList: List<ForecastWheatherApi.Item0>) :
    RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>(){

    class ForecastViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastWheatherApi.Item0) {

            binding.cityTextView.text = item.dtTxt ?: "Unknown Date"

            binding.temperatureTextView.text = "${item.main?.temp ?: "N/A"} °C"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    fun updateData(newForecastList: List<ForecastWheatherApi.Item0>) {
        forecastList = newForecastList
        notifyDataSetChanged()
    }
}