package com.home.wink.weatherapp.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface ForecastsDao {

    @Query("SELECT * FROM forecasts WHERE cityId = :cityId")
    fun getForecastsByCity(cityId: Int): Single<List<ForecastModelDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(forecasts: List<ForecastModelDb>)

    @Query("DELETE FROM forecasts WHERE cityId = :cityId")
    fun deleteAllWithCityId(cityId: Int)
}
