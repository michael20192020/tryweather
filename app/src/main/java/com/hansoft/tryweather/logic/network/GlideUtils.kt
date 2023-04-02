package com.hansoft.tryweather.logic.network

import com.bumptech.glide.load.model.LazyHeaders




object GlideUtils {
    fun glideHeaders(): LazyHeaders? {
        return LazyHeaders.Builder()
            .addHeader("X-RapidAPI-Key", "0632acb3c8mshc564394cf3d6721p11d305jsnc7bf4163b05c")
            .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
            .build()
    }
}