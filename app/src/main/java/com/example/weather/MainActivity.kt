package com.example.weather

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.datasource.PreferencesHelper
import com.example.weather.util.Utility
import com.example.weather.weatherresponse.CurrentWeather
import com.example.weather.weatherresponse.WeatherHourly

/**
 * This activity is used to show the current weather details and showing current weather details.
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private var mWeatherHourlyData = ArrayList<WeatherHourly>()
    lateinit var adapter: WeatherAdapter
    lateinit var preferenceManager: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        setOfflineData()
        subscribeWeatherData()
    }


    /**
     * initialize data related to user interface  and view model and initially
     * i am calling with an api to get the weather reports.
     */
    private fun initializeData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        preferenceManager = PreferencesHelper(this)
        binding.viewModel = mainViewModel
        mWeatherHourlyData.clear()
        adapter = WeatherAdapter(mWeatherHourlyData)
        binding.rvHourlyOutput.adapter = adapter
        mainViewModel.initializeRetrofit()
        if (Utility.isNetworkConnected(this)) {
            binding.pgLoadData.visibility = View.VISIBLE
            mainViewModel.getWetherReports()
        } else
            binding.tvNoHourlyUpdates.visibility = View.VISIBLE
    }

    /**
     * subscribe to weather data
     */
    private fun subscribeWeatherData() {
        mainViewModel.onWeatherData().observe(this, Observer {
            binding.pgLoadData.visibility = View.GONE
            if (it != null) {
                if (it.first != null) {
                    val currentWeather = it.first as CurrentWeather
                    binding.tvWeather.text =
                        """${resources.getString(R.string.wind)} : ${currentWeather.wind_speed}"""
                    binding.tvPressure.text =
                        """${resources.getString(R.string.pressure)} : ${currentWeather.pressure}"""
                    binding.tvHumidity.text =
                        """${resources.getString(R.string.humidity)} : ${currentWeather.humidity}"""
                    preferenceManager.setWind(currentWeather.wind_speed!!)
                    preferenceManager.setHumidity(currentWeather.humidity!!)
                    preferenceManager.setPressure(currentWeather.pressure!!)
                }
                if (it.second != null) {
                    mWeatherHourlyData.addAll(it.second!!)
                    adapter.notifyDataSetChanged()
                    binding.tvNoHourlyUpdates.visibility =
                        if (mWeatherHourlyData.size > 0) View.GONE else View.VISIBLE
                }
            }
        })
    }

    /**
     * set the offline data
     */
    private fun setOfflineData() {
        binding.tvWeather.text =
            """${resources.getString(R.string.wind)} : ${preferenceManager.getWind()}"""
        binding.tvPressure.text =
            """${resources.getString(R.string.pressure)} : ${preferenceManager.getPresssure()}"""
        binding.tvHumidity.text =
            """${resources.getString(R.string.humidity)} : ${preferenceManager.getHumidity()}"""
    }
}