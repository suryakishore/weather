package com.example.weather.weatherresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentWeather {

    @Expose
    @SerializedName("icon")
    val icon: String? = null


    @Expose
    @SerializedName("description")
    val description: String? = null


    @Expose
    @SerializedName("humidity")
    val humidity: String? = null

    @Expose
    @SerializedName("wind_speed")
    val wind_speed: String? = null

    @Expose
    @SerializedName("pressure")
    val pressure: String? = null

}