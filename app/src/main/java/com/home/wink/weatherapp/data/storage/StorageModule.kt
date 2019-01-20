package com.home.wink.weatherapp.data.storage

import android.content.Context
import androidx.room.Room
import com.home.wink.weatherapp.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesStorage(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, context.getString(R.string.forecast_database)).build()

    @Provides
    @Singleton
    fun providesForecastsDao(database: AppDatabase) = database.forecastDao()
}