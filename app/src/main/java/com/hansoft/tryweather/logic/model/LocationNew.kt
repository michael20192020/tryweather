package com.hansoft.tryweather.logic.model

data class LocationNew(val name:String,val region:String,val country:String,val lat: Double,
                       val lon: Double,val tz_id:String,val localtime_epoch:Long,
                       val localtime : String )
