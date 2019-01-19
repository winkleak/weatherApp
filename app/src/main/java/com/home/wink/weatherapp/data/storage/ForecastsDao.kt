package com.home.wink.weatherapp.data.storage

import androidx.room.*
import java.util.*

@Dao
interface ForecastsDao {

    @Query("SELECT * FROM forecasts")
    suspend fun getAllTranslations(): List<Forecast>

    @Query("SELECT * FROM forecasts WHERE dateAdded BETWEEN :dateFrom and :dateTo")
    suspend fun getByDate(dateFrom: Date, dateTo: Date): List<Forecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(translations: List<Forecast>)
}

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long) = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date) = date.time
}