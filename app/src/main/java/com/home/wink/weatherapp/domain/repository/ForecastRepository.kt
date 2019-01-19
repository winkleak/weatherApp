package com.home.wink.weatherapp.domain.repository

import com.home.wink.weatherapp.domain.entity.Forecast
import io.reactivex.Single

interface ForecastRepository {
    fun getForecastsForCity(id: Int): Single<List<Forecast>>
}