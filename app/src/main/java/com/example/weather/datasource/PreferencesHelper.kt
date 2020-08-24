package com.example.weather.datasource

import android.content.Context
import android.content.SharedPreferences
import com.example.Constants.HUMIDITY
import com.example.Constants.PRESSURE
import com.example.Constants.WIND
import com.example.gpsapp.datasource.PreferenceHelperDataSource


/**
 * This class is used to store the data locally which is shared preference
 */
class PreferencesHelper : PreferenceHelperDataSource {
    private val PREF_NAME = "AppSpot"
    var editor: SharedPreferences.Editor
    var sharedPreferences: SharedPreferences

    constructor(context: Context) {
        val PRIVATE_MODE = 0
        sharedPreferences = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = sharedPreferences!!.edit()
        editor.apply()
    }

    override fun setWind(wind: String) {
        editor.putString(WIND, wind)
        editor.commit()
    }

    override fun getWind(): String? {
        return sharedPreferences.getString(WIND, "")

    }

    override fun setPressure(pressure: String) {
        editor.putString(PRESSURE, pressure)
        editor.commit()
    }

    override fun getPresssure(): String? {
        return sharedPreferences.getString(PRESSURE, "")
    }

    override fun setHumidity(humidity: String) {
        editor.putString(HUMIDITY, humidity)
        editor.commit()
    }

    override fun getHumidity(): String? {
        return sharedPreferences.getString(HUMIDITY, "")
    }


}