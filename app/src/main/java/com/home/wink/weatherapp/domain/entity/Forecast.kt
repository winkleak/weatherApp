package com.home.wink.weatherapp.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(
        val city: String,
        val cityId: Int,
        val date: Long,
        val temperature: Double,
        val humidity: Int,
        val pressure: Double,
        val clouds: Int,
        val snow: String?,
        val weather: String,
        val windSpeed: Double,
        val windDirection: Double,
        val iconId: Int
): Parcelable