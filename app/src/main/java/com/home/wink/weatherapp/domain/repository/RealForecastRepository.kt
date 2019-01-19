package com.home.wink.weatherapp.domain.repository

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.storage.ForecastsDao
import com.home.wink.weatherapp.domain.entity.Forecast
import io.reactivex.Single

class RealForecastRepository(api: ForecastApi, forecastsDao: ForecastsDao) : ForecastRepository {

    override fun getForecastsForCity(id: Int): Single<List<Forecast>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}