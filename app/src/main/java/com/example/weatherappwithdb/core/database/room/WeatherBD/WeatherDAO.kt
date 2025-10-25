package com.example.weatherappwithdb.core.database.room.WeatherBD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntityLocation): Long

    @Insert
    suspend fun insertCurrent(weatherEntityCurrent: WeatherEntityCurrent)

    @Delete
    suspend fun delete(weatherEntity: WeatherEntityLocation)

    @Query("DELETE FROM weather_table")
    suspend fun clearAll()

    @Update
    suspend fun update(weatherEntity: WeatherEntityLocation)

    @Upsert
    suspend fun upsertWeather(weatherEntity: WeatherEntityLocation)

    @Transaction
    suspend fun insertWeatherLocationAndCurrent(location: WeatherEntityLocation, current: WeatherEntityCurrent){
        val parentId = insert(location)
        val childData = current.copy(locationId = parentId)
        insertCurrent(childData)
    }

//    @Query("INSERT INTO weather_table WHERE name = :name")
//    suspend fun insertWeatherInDatabaseFromAPI(name: String)

    @Transaction
    @Query("SELECT * FROM weather_table WHERE id = :parentId")
    suspend fun insertToChild(parentId: Long): WeatherChildParent?

    @Query("SELECT * FROM weather_table WHERE name = :locx")
    fun getWeatherByLocation(locx: String): Flow<WeatherEntityLocation>

}