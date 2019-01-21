package com.home.wink.weatherapp.presentation.forecastList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.usecase.GetForecastUseCase
import com.home.wink.weatherapp.navigation.Screens
import com.home.wink.weatherapp.presentation.aac.SingleLiveEvent
import com.home.wink.weatherapp.utils.addTo
import com.home.wink.weatherapp.utils.getCommonErrorDescription
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

class ForecastListViewModel(private val getForecastUseCase: GetForecastUseCase, private val router: Router) : ViewModel() {

    val forecastLoadedLiveData: MutableLiveData<List<Forecast>> by lazy {
        MutableLiveData<List<Forecast>>()
    }

    val errorLiveData: SingleLiveEvent<Int> by lazy {
        SingleLiveEvent<Int>()
    }

    private val disposable = CompositeDisposable()
    private var getForecastDisposable: Disposable? = null

    fun loadForecastForCity(cityId: Int) {
        getForecastDisposable?.dispose()
        getForecastDisposable = getForecastUseCase.getForecastForCity(cityId).subscribe(
            { forecasts ->
                forecastLoadedLiveData.postValue(forecasts)
            },
            {
                errorLiveData.postValue(getCommonErrorDescription(it))
            })
        getForecastDisposable.addTo(disposable)

    }

    fun onForecastClick(forecast: Forecast) {
        router.navigateTo(Screens.ForecastDetail(forecast, forecast.city))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
