package com.home.wink.weatherapp.domain.usecase

import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.repository.ForecastRepository
import io.reactivex.Maybe
import io.reactivex.Observable

class GetAllForecastUseCase(private val repository: ForecastRepository) {

    fun getForecastForCity(cityId: Int): Maybe<List<Forecast>> {
     return repository.getForecastsForCity(cityId)
    }

}
