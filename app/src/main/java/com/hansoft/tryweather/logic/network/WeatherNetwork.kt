package com.hansoft.tryweather.logic.network

import com.hansoft.tryweather.MyApplication
import com.hansoft.tryweather.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object WeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()
    private val weatherService = RetrofitClient.create<WeatherService>()
    private val weatherServiceNew = ServiceCreator.create(WeatherServiceNew::class.java)
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    suspend fun searchWeather(q: String) = weatherService.searchWeather(
        MyApplication.context.getString(R.string.apikey),
        MyApplication.context.getString(R.string.apihost),q).await()
    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherServiceNew.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherServiceNew.getRealtimeWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}