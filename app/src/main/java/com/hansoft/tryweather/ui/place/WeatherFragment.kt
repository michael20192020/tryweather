package com.hansoft.tryweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.hansoft.tryweather.R
import com.hansoft.tryweather.logic.network.GlideUtils
//import kotlinx.android.synthetic.main.fragment_place.*
import kotlinx.android.synthetic.main.fragment_weather.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {
    val weatherViewModel by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*
        etSuburb.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                weatherViewModel.searchSuburb(content)
            } else {
                tvResult.text = ""
            }
        }

         */
        btnSearch.setOnClickListener {
            if (etSuburb.text.isNotEmpty())
            {
                tvResult.text = ""
                weatherViewModel.searchSuburb(etSuburb.text.toString())
            }
        }

        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer{ result ->
            val weather = result.getOrNull()
            if (weather != null) {
                val pos = weather.indexOf("icon :")
                tvResult.text = weather.substring(0,pos)  //" \u2103", "\u2109","\u00B0"
                val url = weather.substring(pos+6).trim()
              //  tvResult.text = tvResult.text.toString() + "\n" + url
               // val glideUrl : GlideUrl = GlideUrl("https://cdn.weatherapi.com/weather/64x64/day/122.png", GlideUtils.glideHeaders())
                //"https://cdn.weatherapi.com/weather/64x64/day/122.png"
                val glideUrl = GlideUrl(url, LazyHeaders.Builder()
                        .addHeader("X-RapidAPI-Key", "0632acb3c8mshc564394cf3d6721p11d305jsnc7bf4163b05c")
                        .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                        .build()
                )
                Glide.with(ivWeather.getContext())
                    .load(glideUrl)
                    .into(ivWeather)
            } else {
                Toast.makeText(activity, "can not find weather information", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            swipeRefresh.isRefreshing = false
        })
       // swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        refreshWeather()
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
    }

    fun refreshWeather() {
        if (etSuburb.text.isNotEmpty())
        {
            tvResult.text = ""
            weatherViewModel.refreshWeather(etSuburb.text.toString())
            swipeRefresh.isRefreshing = true
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherFragment.
         */
        // TODO: Rename and change types and number of parameters
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}