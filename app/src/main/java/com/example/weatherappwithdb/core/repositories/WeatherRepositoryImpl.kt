package com.example.weatherappwithdb.core.repositories

import com.example.weatherappwithdb.core.apis.weatherApi.API_KEY.api_key
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.apis.weatherApi.services.WeatherApi
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData

class WeatherRepositoryImpl (
    private val weatherApi: WeatherApi
): WeatherRepository{
    override suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherData> {
        val responce = weatherApi.getInstance(city, api_key)
        return try {
            if (responce.isSuccessful && responce.body()!=null){
                NetworkResponse.Success(responce.body()!!)
            }
            else{
                NetworkResponse.Error("Error while fetching Data <REPOSITORY>")
            }
        }catch (e: Exception){
            NetworkResponse.Error("Error while fetching Data <REPOSITORY>, ${e.message}")
        }
    }
}