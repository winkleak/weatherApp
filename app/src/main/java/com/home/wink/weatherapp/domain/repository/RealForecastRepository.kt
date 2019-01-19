package com.home.wink.weatherapp.domain.repository

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.storage.ForecastsDao
import com.home.wink.weatherapp.domain.entity.Forecast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RealForecastRepository(val api: ForecastApi, val forecastsDao: ForecastsDao) :
    ForecastRepository {

    override fun getForecastsForCity(id: Int): Single<List<Forecast>> {
        return api.getForecastFromCity(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { response ->
                Single.just(response.toForecasts())
            }
    }

}