package com.hansoft.tryweather.logic.dao

import android.content.Context
import com.google.gson.Gson
import com.hansoft.tryweather.MyApplication
import com.hansoft.tryweather.logic.model.Place

object PlaceDao {
    private fun sharedPreferences() =
        MyApplication.context.getSharedPreferences("weather", Context.MODE_PRIVATE)

    fun isPlaceSaved() = sharedPreferences().contains("place")

    fun savePlace(place: Place) {
        sharedPreferences().edit().apply { putString("place", Gson().toJson(place)) }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

}