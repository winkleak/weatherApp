package com.home.wink.weatherapp

import android.app.Application
import com.home.wink.weatherapp.di.AppComponent
import com.home.wink.weatherapp.di.AppModule
import com.home.wink.weatherapp.di.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}