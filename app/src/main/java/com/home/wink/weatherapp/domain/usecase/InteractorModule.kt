package com.home.wink.weatherapp.domain.usecase

import com.home.wink.weatherapp.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
    class InteractorModule {

        @Singleton
        @Provides
        fun provideGetForecastInteractor(forecastRepository: ForecastRepository): GetAllForecastUseCase = GetAllForecastUseCase(forecastRepository)

    }
