package com.home.wink.weatherapp.domain.repository

import com.home.wink.weatherapp.domain.entity.Forecast
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface ForecastRepository {
    fun getForecastsForCity(cityId: Int): Maybe<List<Forecast>>
}