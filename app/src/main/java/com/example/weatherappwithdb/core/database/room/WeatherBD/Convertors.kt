package com.example.weatherappwithdb.core.database.room.WeatherBD

import androidx.room.TypeConverter
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.google.gson.Gson

class Convertors {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherDataClass(weatherData: WeatherData): String{
        return gson.toJson(weatherData)
    }

    @TypeConverter
    fun toWeatherDataClass(json: String): WeatherData{
        return gson.fromJson(json, WeatherData::class.java)
    }
}