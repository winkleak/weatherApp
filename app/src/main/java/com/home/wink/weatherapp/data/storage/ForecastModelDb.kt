package com.home.wink.weatherapp.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
class ForecastModelDb(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val date: Long,
        val city: String,
        val cityId: Int,
        val temperature: Double,
        val humidity: Int,
        val pressure: Double,
        val clouds: Int,
        val snow: String?,
        val weather: String,
        val windSpeed: Double,
        val windDirection: Double,
        val iconId: Int,
        val refreshingDate: Long
)
