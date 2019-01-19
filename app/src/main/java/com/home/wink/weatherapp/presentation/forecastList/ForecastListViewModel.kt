package com.home.wink.weatherapp.presentation.forecastList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.usecase.GetAllForecastUseCase
import com.home.wink.weatherapp.domain.usecase.base.UseCase

class ForecastListViewModel(val getAllForecastUseCase: GetAllForecastUseCase): ViewModel(), UseCase.Callback<List<Forecast>> {
    val forecastLoadedLiveData: MutableLiveData<List<Forecast>> by lazy {
        MutableLiveData<List<Forecast>>()
    }

    override fun onSuccess(response: List<Forecast>) {
       forecastLoadedLiveData.postValue(response)
    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCleared() {
        super.onCleared()
        //disposable.clear()
    }

    fun loadForecastForCity(cityId: Int) {
        getAllForecastUseCase.execute(cityId, this)
    }

}
