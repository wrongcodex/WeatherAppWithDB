package com.example.weatherappwithdb.core.database.room.WeatherBD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

@Database(
    entities = [WeatherEntityLocation::class, WeatherEntityCurrent::class],
    version = 2,
    exportSchema = false
)
//@TypeConverter(Convertors::class.java)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao(): WeatherDAO
}