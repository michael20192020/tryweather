package com.hansoft.tryweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hansoft.tryweather.logic.Repository
import com.hansoft.tryweather.logic.model.Location

class WeatherViewModel : ViewModel() {
    private val suburbLiveData = MutableLiveData<String>()
    val weatherLiveData = Transformations.switchMap(suburbLiveData) { query ->
        Repository.searchWeather(query)
    }

    fun searchSuburb(query: String) {
        suburbLiveData.value = query
    }

    fun refreshWeather(query: String) {
        suburbLiveData.value = query
    }
}