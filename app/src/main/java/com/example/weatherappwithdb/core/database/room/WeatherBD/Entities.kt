package com.example.weatherappwithdb.core.database.room.WeatherBD

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.weatherappwithdb.core.models.weatherApiModel.Condition

@Entity(tableName = "weather_table")
data class WeatherEntityLocation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")val id: Long = 0,
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)

@Entity(tableName = "weather_current",
    foreignKeys = [ForeignKey(
        entity = WeatherEntityLocation::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("locationId"),
        onDelete = ForeignKey.CASCADE,
        //onUpdate = ForeignKey.CASCADE
    )]
)
data class WeatherEntityCurrent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("locationId", index = true)val locationId: Long,
    val cloud: Int,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Double,
    val is_day: Double,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Int,
    val vis_miles: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
)

data class WeatherChildParent(
    @Embedded val parent: WeatherEntityLocation,
    @Relation(
        parentColumn = "id",
        entityColumn = "locationId"
    )
    val child: List<WeatherEntityCurrent>
)