package com.home.wink.weatherapp.di

import com.home.wink.weatherapp.data.repository.ForecastRepository
import com.home.wink.weatherapp.domain.usecase.GetForecastUseCase
import com.home.wink.weatherapp.domain.usecase.RealGetForecastUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetForecastUseCase(forecastRepository: ForecastRepository): GetForecastUseCase =
        RealGetForecastUseCase(forecastRepository)

}
