package com.example.weatherappwithdb.core.database.room.WeatherBD

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "My Weather Database"

    @Singleton
    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ): RoomDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }


    @Singleton
    @Provides
    fun provideWeatherDao(roomDatabase: WeatherDatabase): WeatherDAO{
        return roomDatabase.weatherDao()
    }



}