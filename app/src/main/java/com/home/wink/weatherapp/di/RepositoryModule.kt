package com.home.wink.weatherapp.di

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.repository.ForecastRepository
import com.home.wink.weatherapp.data.repository.RealForecastRepository
import com.home.wink.weatherapp.data.storage.ForecastsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideForecastRepository(api: ForecastApi, forecastsDao: ForecastsDao): ForecastRepository =
        RealForecastRepository(api, forecastsDao)
}