package com.home.wink.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.wink.weatherapp.domain.interactor.ForecastInteractor
import com.home.wink.weatherapp.presentation.forecastList.ForecastListViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(
        private val forecastInteractor: ForecastInteractor,
        private val router: Router
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ForecastListViewModel::class.java) -> ForecastListViewModel(forecastInteractor)
        else -> throw AssertionError("Unknown model class $modelClass")
    } as T
}