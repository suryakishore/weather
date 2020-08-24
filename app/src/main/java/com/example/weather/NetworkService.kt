package com.example.weather

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * This interface is used to call the weather details api.
 */
interface NetworkService {
    @POST("timemachine")
    fun getAppWeatherResults(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("dt") dt: Long,
        @Query("appid") appid: String
    ): Observable<Response<ResponseBody?>>
}