package com.hansoft.tryweather.logic

import androidx.lifecycle.liveData
import com.hansoft.tryweather.logic.model.Place
import com.hansoft.tryweather.logic.network.Network
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = Network.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun searchWeather(q:String) = liveData(Dispatchers.IO) {
        val result = try {
            val weatherResponse = Network.searchWeather(q)
            if (weatherResponse != null) {
                val current = weatherResponse.current
                val temperature = current.temp_c
                val condition = current.condition
                val text = condition.text + "\n $temperature"
                Result.success(text)
            } else {
                Result.failure(RuntimeException("response is null"))
            }
        } catch (e: Exception) {
            Result.failure<String>(e)
        }
        emit(result)
    }
}