package com.home.wink.weatherapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Forecast(
    val date: Int,
    val temperature: Double,
    val humidity: Int,
    val pressure: Int,
    val clouds: Int,
 //   val time: Long,
    val weather: String,
    val windSpeed: Double,
    val windDirection: WindDirection
)