package com.home.wink.weatherapp.domain.usecase

import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.repository.ForecastRepository
import com.home.wink.weatherapp.domain.usecase.base.UseCase

class GetAllForecastUseCase(val repository: ForecastRepository): UseCase<Int, List<Forecast>> {
    override fun execute(cityId: Int, callback: UseCase.Callback<List<Forecast>>) {
       repository.getForecastsForCity(cityId).subscribe({ forecasts ->
           callback.onSuccess(forecasts)
       },
           {
               callback.onError(it)
           })
    }

}
