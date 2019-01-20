package com.home.wink.weatherapp.navigation

import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.presentation.forecastDetail.ForecastDetailFragment
import com.home.wink.weatherapp.presentation.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object MainFlow : SupportAppScreen() {
        override fun getFragment() = MainFragment.newInstance()
    }

    data class ForecastDetail(
            val forecast: Forecast,
            val cityName: String
    ) : SupportAppScreen() {
        override fun getFragment() = ForecastDetailFragment.newInstance(forecast, cityName)
    }
}