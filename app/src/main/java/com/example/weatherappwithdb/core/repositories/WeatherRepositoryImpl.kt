package com.example.weatherappwithdb.core.repositories

import android.util.Log
import com.example.weatherappwithdb.core.apis.weatherApi.API_KEY.api_key
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.apis.weatherApi.RetrofitInstance
import com.example.weatherappwithdb.core.database.room.WeatherBD.WeatherDAO
import com.example.weatherappwithdb.core.database.room.WeatherBD.WeatherEntityLocation
import com.example.weatherappwithdb.core.models.weatherApiModel.Current
import com.example.weatherappwithdb.core.models.weatherApiModel.Location
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData

class WeatherRepositoryImpl (
    private val weatherDAO: WeatherDAO
): WeatherRepository{
    //private val weatherApi: WeatherApi
    private val weatherApi = RetrofitInstance.weatherApi
    override suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherData> {
        val responce = weatherApi.getInstance(city, api_key)
        return try {
            if (responce.isSuccessful && responce.body()!=null){
                Log.d("fdafaghjd", "getDataVM: ${responce.body()}")

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