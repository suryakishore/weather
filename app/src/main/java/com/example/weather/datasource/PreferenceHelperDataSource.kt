package com.example.gpsapp.datasource

/**
 * This interface is used to show the call back methods for shared preference class.
 */
interface PreferenceHelperDataSource {

    fun setWind(appSpotData: String)

    fun getWind(): String?

    fun setPressure(appSpotData: String)

    fun getPresssure(): String?

    fun setHumidity(appSpotData: String)

    fun getHumidity(): String?

}