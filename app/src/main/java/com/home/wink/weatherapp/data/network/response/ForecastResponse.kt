package com.home.wink.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
        @SerializedName("city")
        val city: City,
        @SerializedName("cnt")
        val cnt: Int,
        @SerializedName("cod")
        val cod: String,
        @SerializedName("list")
        val list: List<X>,
        @SerializedName("message")
        val message: Double
) {
    data class City(
            @SerializedName("coord")
            val coord: Coord,
            @SerializedName("country")
            val country: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
    ) {
        data class Coord(
                @SerializedName("lat")
                val lat: Double,
                @SerializedName("lon")
                val lon: Double
        )
    }

    data class X(
            @SerializedName("clouds")
            val clouds: Clouds,
            @SerializedName("dt")
            val dt: Int,
            @SerializedName("dt_txt")
            val dtTxt: String,
            @SerializedName("main")
            val main: Main,
            @SerializedName("snow")
            val snow: Snow,
            @SerializedName("sys")
            val sys: Sys,
            @SerializedName("weather")
            val weather: List<Weather>,
            @SerializedName("wind")
            val wind: Wind
    ) {
        data class Sys(
                @SerializedName("pod")
                val pod: String
        )

        data class Main(
                @SerializedName("grnd_level")
                val grndLevel: Int,
                @SerializedName("humidity")
                val humidity: Int,
                @SerializedName("pressure")
                val pressure: Int,
                @SerializedName("sea_level")
                val seaLevel: Double,
                @SerializedName("temp")
                val temp: Double,
                @SerializedName("temp_kf")
                val tempKf: Int,
                @SerializedName("temp_max")
                val tempMax: Double,
                @SerializedName("temp_min")
                val tempMin: Double
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

        class Snow(
        )
    }
}