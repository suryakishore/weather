package com.example.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemWeatherBinding
import com.example.weather.weatherresponse.WeatherHourly


/**
 * Adapter class for the hourly weather  items.
 */
public class WeatherAdapter(data: ArrayList<WeatherHourly>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var mWeatherData: ArrayList<WeatherHourly>
    lateinit var context: Context

    init {
        mWeatherData = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemWeatherBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_weather, parent, false)
        return WeatherViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mWeatherData != null) mWeatherData.size else 0
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        val weatherResponse = mWeatherData.get(position)
        if (weatherResponse != null) {
            holder.itemBinding.tvWeather.text =
                """${context.resources.getString(R.string.wind)} : ${weatherResponse.wind_speed}"""
            holder.itemBinding.tvHumidity.text =
                """${context.resources.getString(R.string.humidity)} : ${weatherResponse.humidity}"""
            holder.itemBinding.tvPressure.text =
                """${context.resources.getString(R.string.pressure)} : ${weatherResponse.pressure}"""
        }
    }

    /**
     * view holder class for weather detail items
     */
    class WeatherViewHolder(itemBinding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: ItemWeatherBinding

        init {
            this.itemBinding = itemBinding
        }
    }
}