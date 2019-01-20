package com.home.wink.weatherapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ForecastModelDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastsDao

}