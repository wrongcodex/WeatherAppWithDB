package com.example.weatherappwithdb.core.apis.weatherApi.services


import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
    @GET("/current.json")
    suspend fun getInstance(
        @Query("q") city: String,
        @Query("key") apiKey: String,
        @Query("aqi") aqi: String = "yes"
    ): Response<WeatherData>
}