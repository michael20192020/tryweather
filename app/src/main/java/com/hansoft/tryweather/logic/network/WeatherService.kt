package com.hansoft.tryweather.logic.network

import com.hansoft.tryweather.MyApplication
import com.hansoft.tryweather.R
import com.hansoft.tryweather.logic.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {
    /*
    @Headers("Content-Type: application/json",
    "X-RapidAPI-Key: ${MyApplication.context.getString(R.string.apikey)}",
    "X-RapidAPI-Host: ${MyApplication.context.getString(R.string.apihost)}")

     */
    @Headers("Content-Type: application/json")
    @GET("current.json")
    fun searchWeather(@Header("X-RapidAPI-Key") key: String, @Header("X-RapidAPI-Host") host: String,
                      @Query("q") q: String): Call<WeatherResponse>


}