package com.example.weatherappwithdb.core.repositories.repo

import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherData>
    //suspend fun getWeatherFromDB()
}