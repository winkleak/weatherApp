package com.home.wink.weatherapp.data.repository

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.network.response.ForecastNetworkModel
import com.home.wink.weatherapp.data.storage.ForecastModelDb
import com.home.wink.weatherapp.data.storage.ForecastsDao
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.utils.toForecasts
import com.home.wink.weatherapp.utils.toForecastsDbModel
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import java.util.*

class RealForecastRepository(private val api: ForecastApi, private val forecastsDao: ForecastsDao) :
        ForecastRepository {
    private val fiveMinutesInMillis = 5 * 60 * 60 * 1000

    override fun getForecastsForCity(cityId: Int): Maybe<List<Forecast>> {
        val dbSource = forecastsDao.getForecastsByCity(cityId)
                .filter { forecastsDb ->
                    !forecastsDb.isEmpty() && Date().time - forecastsDb.first().refreshingDate < fiveMinutesInMillis
                }
                .map(List<ForecastModelDb>::toForecasts)

        val networkSource = api.getForecastFromCity(cityId)
                .map(ForecastNetworkModel::toForecastsDbModel)
                .doOnSuccess { forecastDbModel ->
                        forecastsDao.deleteAllWithCityId(cityId)
                        forecastsDao.insertAll(forecastDbModel)
                }.map(List<ForecastModelDb>::toForecasts)

        return Maybe.concat(dbSource, networkSource.toMaybe())
                .firstElement()
                .subscribeOn(Schedulers.io())
    }
}





