package com.home.wink.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.home.wink.weatherapp.BuildConfig
import com.home.wink.weatherapp.data.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesStorage(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.FORECAST_DB_NAME).build()

    @Provides
    @Singleton
    fun providesForecastsDao(database: AppDatabase) = database.forecastDao()
}