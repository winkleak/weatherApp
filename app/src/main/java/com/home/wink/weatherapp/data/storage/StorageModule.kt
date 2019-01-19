package com.home.wink.weatherapp.data.storage

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesStorage(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "translations-database").build()

    @Provides
    @Singleton
    fun providesForecastsDao(database: AppDatabase) = database.forecastDao()
}