package com.example.wheatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.databinding.ForecastItemsBinding
import com.example.wheatherapp.model.ForecastWheatherApi

class ForecastRecyclerViewAdapter(
    private var data: List<ForecastWheatherApi.Item0>
) : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(private val binding: ForecastItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastWheatherApi.Item0) {
            binding.dateTextView.text = item.dtTxt ?: "N/A"
            binding.temperatureTextView.text = "${item.main?.temp ?: "N/A"}°C"
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
