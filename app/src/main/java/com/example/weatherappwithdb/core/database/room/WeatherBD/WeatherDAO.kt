package com.example.weatherappwithdb.core.database.room.WeatherBD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDAO {
    @Insert
    fun insert(weatherEntity: WeatherEntityLocation)
    @Delete
    fun delete(weatherEntity: WeatherEntityLocation)
    @Update
    fun update(weatherEntity: WeatherEntityLocation)

//    @Query("DELETE FROM weather_table ")
//    fun deleteAllNotes()

    @Query("SELECT * FROM weather_table WHERE name = :locx")
    suspend fun getWeatherByLocation(locx: String): WeatherEntityLocation
}