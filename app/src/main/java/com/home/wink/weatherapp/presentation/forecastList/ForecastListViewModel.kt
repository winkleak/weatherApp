package com.home.wink.weatherapp.presentation.forecastList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.entity.ResponseError
import com.home.wink.weatherapp.domain.usecase.GetAllForecastUseCase
import com.home.wink.weatherapp.navigation.Screens
import com.home.wink.weatherapp.presentation.forecastDetail.ForecastDto
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

class ForecastListViewModel(private val getAllForecastUseCase: GetAllForecastUseCase, private val router: Router) : ViewModel() {

    val forecastLoadedLiveData: MutableLiveData<List<Forecast>> by lazy {
        MutableLiveData<List<Forecast>>()
    }
    val errorLiveData: MutableLiveData<ResponseError> by lazy {
        MutableLiveData<ResponseError>()
    }

    private val disposable = CompositeDisposable()
    private var getForecastObservable: Disposable? = null

    fun loadForecastForCity(cityId: Int) {
        getForecastObservable = null
        getForecastObservable = getAllForecastUseCase.getForecastForCity(cityId).subscribe(
                { forecasts ->
                    forecastLoadedLiveData.postValue(forecasts)
                },
                {
                    errorLiveData.postValue(ResponseError.UNDEFINED_ERROR)
                })
        disposable.addAll(getForecastObservable)
    }

    fun navigateToDetail(forecast: Forecast) {
        router.navigateTo(Screens.ForecastDetail(ForecastDto(
                forecast.city,
                forecast.date,
                forecast.temperature,
                forecast.humidity,
                forecast.pressure,
                forecast.clouds,
                forecast.snow,
                forecast.weather,
                forecast.windSpeed,
                forecast.windDirection,
                forecast.iconId
        ), forecast.city))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
