package com.home.wink.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun providesAppContext() = appContext
}