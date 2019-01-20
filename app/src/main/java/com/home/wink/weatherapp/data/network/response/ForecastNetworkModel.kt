package com.home.wink.weatherapp.data.network.response

data class ForecastNetworkModel(
        val city: City,
        val cnt: Int,
        val cod: String,
        val list: List<X>,
        val message: Double
) {
    data class City(
            val coord: Coord,
            val country: String,
            val id: Int,
            val name: String
    ) {
        data class Coord(
                val lat: Double,
                val lon: Double
        )
    }

    data class X(
            val clouds: Clouds,
            val dt: Int,
            val dtTxt: String,
            val main: Main,
            val snow: Snow?,
            val sys: Sys,
            val weather: List<Weather>,
            val wind: Wind
    ) {
        data class Sys(
                val pod: String
        )

        data class Main(
                val grndLevel: Double,
                val humidity: Int,
                val pressure: Double,
                val seaLevel: Double,
                val temp: Double,
                val tempKf: Double,
                val tempMax: Double,
                val tempMin: Double
        )

        data class Weather(
                val description: String,
                val icon: String,
                val id: Int,
                val main: String
        )

        data class Clouds(
                val all: Int
        )

        data class Wind(
                val deg: Double,
                val speed: Double
        )

        object Snow
    }
}