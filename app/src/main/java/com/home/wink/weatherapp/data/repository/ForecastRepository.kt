package com.home.wink.weatherapp.data.repository

import com.home.wink.weatherapp.domain.entity.Forecast
import io.reactivex.Maybe

interface ForecastRepository {
    fun getForecastsForCity(cityId: Int): Maybe<List<Forecast>>
}