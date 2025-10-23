package com.example.weatherappwithdb.core.database.room.WeatherBD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntityLocation)
    @Delete
    fun delete(weatherEntity: WeatherEntityLocation)
    @Update
    fun update(weatherEntity: WeatherEntityLocation)

//    @Query("DELETE FROM weather_table ")
//    fun deleteAllNotes()

    @Query("INSERT INTO weather_table WHERE name = :name")
    suspend fun insertWeatherInDatabaseFromAPI(name: String)

    @Query("SELECT * FROM weather_table WHERE name = :locx")
    suspend fun getWeatherByLocation(locx: String): WeatherEntityLocation

    ye kis lie code hai
    sir ya fetch or insert krna ka lya
    name kis purpose k lie add ho rha
    name as a peremeter identify kra aga
}