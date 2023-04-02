package com.hansoft.tryweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://weatherapi-com.p.rapidapi.com/"
    private const val PICTURE_URL = "https://cdn.weatherapi.com/weather/64x64/day/116.png"
    var isPicture = false
    private val retrofit = Retrofit.Builder()
        .baseUrl(if (isPicture) PICTURE_URL else BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}