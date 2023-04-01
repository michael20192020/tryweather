package com.hansoft.tryweather.logic.network

import com.hansoft.tryweather.logic.model.PlaceResponse
import com.hansoft.tryweather.logic.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {
    @Headers("Content-Type: application/json",
        "X-RapidAPI-Key: 0632acb3c8mshc564394cf3d6721p11d305jsnc7bf4163b05c",
    "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com")
    @GET("current.json")
    fun searchWeather(@Query("q") q: String): Call<WeatherResponse>
}