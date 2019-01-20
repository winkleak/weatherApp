package com.home.wink.weatherapp.domain.repository

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.storage.ForecastsDao
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.utils.toForecasts
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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
                .flatMap { forecastsDb ->
                    Maybe.just(forecastsDb.toForecasts())
                }
                .subscribeOn(Schedulers.computation())

        val networkSource = api.getForecastFromCity(cityId)
                .map { forecastNetworkModel ->
                    val forecastModelDb = forecastNetworkModel.toForecastsDbModel()
                    Observable.create<Any> { subscriber ->
                        forecastsDao.deleteAllWithCityId(cityId)
                        forecastsDao.insertAll(forecastModelDb)
                        subscriber.onComplete()
                    }.subscribeOn(Schedulers.computation()).subscribe()
                    return@map forecastModelDb.toForecasts()
                }.subscribeOn(Schedulers.io())

        return Maybe.concat(dbSource, networkSource.toMaybe())
                .firstElement()
                .subscribeOn(AndroidSchedulers.mainThread())
    }
}





