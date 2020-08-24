package com.example.weather

import android.util.Log
import androidx.core.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Constants.API_KEY
import com.example.Constants.LANG
import com.example.Constants.LAT
import com.example.Constants.SUCCESS
import com.example.weather.weatherresponse.CurrentWeather
import com.example.weather.weatherresponse.WeatherHourly
import com.example.weather.weatherresponse.WeatherResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


/**
 * view model class for the main activity.
 */
class MainViewModel() : ViewModel() {

    private var networkService: NetworkService? = null
    private val mWeatherData = MutableLiveData<Pair<CurrentWeather, ArrayList<WeatherHourly>>>()

    public fun initializeRetrofit() {
        networkService = NetworkManager.initializeBaseUrlRetrofit()
    }

    /**
     * This method used to get the search Data.
     */
    public fun getWetherReports() {
        networkService.also { it ->
            it!!.getAppWeatherResults(
                LAT, LANG, System.currentTimeMillis() / 1000, API_KEY
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            Log.d("exe", "code  " + code)
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                val gson = Gson()
                                Log.d("exe", "response  " + response)
                                val weatherResponse =
                                    gson.fromJson(
                                        response,
                                        WeatherResponse::class.java)
                                if (weatherResponse != null && weatherResponse.current != null && weatherResponse.hourly != null) {
                                    mWeatherData.postValue(
                                        Pair.create(
                                            weatherResponse.current,
                                            weatherResponse.hourly
                                        )
                                    )
                                }
                            } else {
                                mWeatherData.postValue(null)
                                jsonObject = JSONObject(it.errorBody()!!.string())
                            }
                        } catch (e: JSONException) {
                            mWeatherData.postValue(null)
                        } catch (e: IOException) {
                            mWeatherData.postValue(null)
                        }
                    }
                }
        }
    }


    /**
     * notify activity when weather data comes
     */
    fun onWeatherData(): MutableLiveData<Pair<CurrentWeather, ArrayList<WeatherHourly>>> {
        return mWeatherData
    }


}