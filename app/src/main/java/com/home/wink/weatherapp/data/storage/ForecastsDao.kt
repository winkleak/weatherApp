package com.home.wink.weatherapp.data.storage

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface ForecastsDao {

    @Query("SELECT * FROM forecasts WHERE cityId = :cityId")
    fun getForecastsByCity(cityId: Int): Maybe<List<ForecastModelDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(forecasts: List<ForecastModelDb>)

    @Query( "DELETE FROM forecasts WHERE cityId = :cityId")
    fun deleteAllWithCityId(cityId: Int)
}
