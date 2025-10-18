package com.example.weatherappwithdb.core.database.room.WeatherBD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntityLocation::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao(): WeatherDAO

    companion object{
        private var instance : WeatherDatabase? = null

        @Synchronized
        fun getInstance(context: Context): WeatherDatabase{
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).fallbackToDestructiveMigration(false).build()
            return instance!!
        }
    }
}