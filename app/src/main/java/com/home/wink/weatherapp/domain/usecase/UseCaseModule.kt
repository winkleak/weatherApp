package com.home.wink.weatherapp.domain.usecase

import com.home.wink.weatherapp.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
    class UseCaseModule {

        @Singleton
        @Provides
        fun provideGetForecastUseCase(forecastRepository: ForecastRepository): GetAllForecastUseCase = GetAllForecastUseCase(forecastRepository)

    }
