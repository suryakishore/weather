package com.example.weather.weatherresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @Expose
    @SerializedName("lat")
    val lat: String? = null

    @Expose
    @SerializedName("lon")
    val lon: String? = null


    @Expose
    @SerializedName("timezone")
    val timezone: String? = null


    @Expose
    @SerializedName("timezone_offset")
    val timezone_offset: String? = null

    @Expose
    @SerializedName("current")
    val current: CurrentWeather? = null

    @Expose
    @SerializedName("hourly")
    val hourly: ArrayList<WeatherHourly>? = null


}