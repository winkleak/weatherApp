package com.home.wink.weatherapp.data.network


import com.home.wink.weatherapp.data.network.response.ForecastNetworkModel
import io.reactivex.Single
import retrofit2.http.*

private const val FORECAST = "forecast"

interface ForecastApi {

    @GET(FORECAST)
    fun getForecastFromCity(
            @Query("id") cityId: Int,
            @Query("appid") appId: String = "efd66279846b8371ea4c73309c8c150f"
    ): Single<ForecastNetworkModel>

}