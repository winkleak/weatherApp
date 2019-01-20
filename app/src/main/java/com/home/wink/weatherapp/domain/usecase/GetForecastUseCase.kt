package com.home.wink.weatherapp.domain.usecase

import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.repository.ForecastRepository
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers

interface GetForecastUseCase{
    fun getForecastForCity(cityId: Int): Maybe<List<Forecast>>
}

class RealGetForecastUseCase(private val repository: ForecastRepository): GetForecastUseCase {

    override fun getForecastForCity(cityId: Int): Maybe<List<Forecast>> {
        return repository.getForecastsForCity(cityId)
                .observeOn(AndroidSchedulers.mainThread())
    }
}
