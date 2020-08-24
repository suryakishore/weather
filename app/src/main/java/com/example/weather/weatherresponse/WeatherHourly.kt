package com.example.weather.weatherresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherHourly {

    @Expose
    @SerializedName("dt")
    val dt: String? = null


    @Expose
    @SerializedName("temp")
    val temp: String? = null


    @Expose
    @SerializedName("wind_deg")
    val wind_deg: String? = null

    @Expose
    @SerializedName("dew_point")
    val dew_point: String? = null

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