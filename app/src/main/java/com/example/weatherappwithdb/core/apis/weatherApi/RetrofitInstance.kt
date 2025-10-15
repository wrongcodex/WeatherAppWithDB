package com.example.weatherappwithdb.core.apis.weatherApi

import com.example.weatherappwithdb.core.apis.weatherApi.services.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_url = "http://api.weatherapi.com/v1"

    private fun getWeatherResponse() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val weatherApi: WeatherApi = getWeatherResponse().create(WeatherApi::class.java)
}