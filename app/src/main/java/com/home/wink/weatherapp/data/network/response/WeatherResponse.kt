package com.home.wink.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("cod")
        val cod: Int,
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: Main,
        @SerializedName("name")
        val name: String,
        @SerializedName("rain")
        val rain: Rain,
        @SerializedName("sys")
        val sys: Sys,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind")
        val wind: Wind
) {
    data class Coord(
            @SerializedName("lat")
            val lat: Int,
            @SerializedName("lon")
            val lon: Int
    )

    data class Weather(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("main")
            val main: String
    )

    data class Rain(
            @SerializedName("3h")
            val h: Int
    )

    data class Main(
            @SerializedName("humidity")
            val humidity: Int,
            @SerializedName("pressure")
            val pressure: Int,
            @SerializedName("temp")
            val temp: Double,
            @SerializedName("temp_max")
            val tempMax: Double,
            @SerializedName("temp_min")
            val tempMin: Double
    )

    data class Clouds(
            @SerializedName("all")
            val all: Int
    )

    data class Wind(
            @SerializedName("deg")
            val deg: Double,
            @SerializedName("speed")
            val speed: Double
    )

    data class Sys(
            @SerializedName("country")
            val country: String,
            @SerializedName("sunrise")
            val sunrise: Int,
            @SerializedName("sunset")
            val sunset: Int
    )
}