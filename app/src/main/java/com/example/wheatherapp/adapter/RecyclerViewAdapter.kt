package com.example.wheatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.databinding.ItemsBinding
import com.example.wheatherapp.model.CurrentWheatherApi

class RecyclerViewAdapter(
    private var data: List<CurrentWheatherApi>
) : RecyclerView.Adapter<RecyclerViewAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrentWheatherApi) {
            binding.cityTextView.text = item.name ?: "Unknown City"
            binding.temperatureTextView.text = "${item.main?.temp ?: "N/A"}°C"
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