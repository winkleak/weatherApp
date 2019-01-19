package com.home.wink.weatherapp.data.network


import com.home.wink.weatherapp.data.network.response.ForecastResponse
import com.home.wink.weatherapp.data.network.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.*

const val BASE_URL = "api.openweathermap.org/data/2.5/"
private const val WEATHER = "weather"
private const val FORECAST = "forecast"

interface ForecastApi {

    @GET(WEATHER)
    fun getWeatherFromCity(
        @Query("id") cityId: Int
    ): Single<WeatherResponse>

    @GET(FORECAST)
    fun getForecastFromCity(
        @Query("id") cityId: Int
    ): Single<ForecastResponse>
}