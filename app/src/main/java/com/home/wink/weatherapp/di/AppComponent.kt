package com.home.wink.weatherapp.di

import com.home.wink.weatherapp.data.network.NetworkModule
import com.home.wink.weatherapp.data.storage.StorageModule
import com.home.wink.weatherapp.domain.usecase.InteractorModule
import com.home.wink.weatherapp.domain.repository.RepositoryModule
import com.home.wink.weatherapp.navigation.NavigationModule
import com.home.wink.weatherapp.presentation.forecastList.ForecastListFragment
import com.home.wink.weatherapp.presentation.main.AppActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    AppModule::class,
    StorageModule::class,
    InteractorModule::class,
    RepositoryModule::class,
    NavigationModule::class
])
interface AppComponent {
    fun inject(appActivity: AppActivity)

    fun inject(forecastListFragment: ForecastListFragment)

}