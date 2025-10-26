package com.example.weatherappwithdb.core.repositories.impl

import android.util.Log
import com.example.weatherappwithdb.core.apis.weatherApi.API_KEY
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.apis.weatherApi.services.WeatherApi
import com.example.weatherappwithdb.core.database.room.WeatherBD.WeatherDAO
import com.example.weatherappwithdb.core.database.room.WeatherBD.WeatherEntityCurrent
import com.example.weatherappwithdb.core.database.room.WeatherBD.WeatherEntityLocation
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.example.weatherappwithdb.core.repositories.repo.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl@Inject constructor(
    private val weatherDao: WeatherDAO,
    private val weatherApi: WeatherApi
): WeatherRepository {
    //private val weatherApi: WeatherApi

    override suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherData> {
        val response = weatherApi.getInstance(city, API_KEY.api_key)
        return try {
            if (response.isSuccessful && response.body()!=null){
                val data = response.body()!!
                Log.d("fdafaghjd", "getDataVM: ${response.body()}")
                val location = WeatherEntityLocation(
                    country = data.location.country,
                    lat = data.location.lat,
                    localtime = data.location.localtime,
                    localtime_epoch = data.location.localtime_epoch,
                    lon = data.location.lon,
                    name = data.location.name,
                    region = data.location.region,
                    tz_id = data.location.tz_id
                )

                val current = WeatherEntityCurrent(
                    locationId = 0,
                    cloud = data.current.cloud,
                    dewpoint_c = data.current.dewpoint_c,
                    dewpoint_f = data.current.dewpoint_f,
                    feelslike_c = data.current.feelslike_c,
                    feelslike_f = data.current.feelslike_f,
                    gust_kph = data.current.gust_kph,
                    gust_mph = data.current.gust_mph,
                    heatindex_c = data.current.heatindex_c,
                    heatindex_f = data.current.heatindex_f,
                    humidity = data.current.humidity,
                    is_day = data.current.is_day,
                    last_updated = data.current.last_updated,
                    last_updated_epoch = data.current.last_updated_epoch,
                    precip_in = data.current.precip_in,
                    precip_mm = data.current.precip_mm,
                    pressure_in = data.current.pressure_in,
                    pressure_mb = data.current.pressure_mb,
                    temp_c = data.current.temp_c,
                    temp_f = data.current.temp_f,
                    uv = data.current.uv,
                    vis_km = data.current.vis_km,
                    vis_miles = data.current.vis_miles,
                    wind_degree = data.current.wind_degree,
                    wind_dir = data.current.wind_dir,
                    wind_kph = data.current.wind_kph,
                    wind_mph = data.current.wind_mph,
                    windchill_c = data.current.windchill_c,
                    windchill_f = data.current.windchill_f
                )
                weatherDao.insertWeatherLocationAndCurrent(
                    location, current
                )
                NetworkResponse.Success(response.body()!!)
            }
            else{
                NetworkResponse.Error("Error while fetching Data <REPOSITORY>")
            }
        }catch (e: Exception){
            NetworkResponse.Error("Error while fetching Data <REPOSITORY>, ${e.message}")
        }
    }
}