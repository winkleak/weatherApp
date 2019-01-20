package com.home.wink.weatherapp.data.network.response

import com.home.wink.weatherapp.data.storage.ForecastModelDb
import com.home.wink.weatherapp.utils.imageIdFromWeather
import com.home.wink.weatherapp.utils.kelvinToCelsius
import com.home.wink.weatherapp.utils.timeInMillisFromSeconds
import com.home.wink.weatherapp.domain.entity.Forecast
import java.util.*

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

        class Snow
    }

    fun toForecastsDbModel(): List<ForecastModelDb> {
        val forecasts: MutableList<ForecastModelDb> = mutableListOf()
        val refreshingDate = Calendar.getInstance().time.time
        list.forEach { responseForecast ->
            forecasts.add(
                    ForecastModelDb(
                            city = city.name,
                            cityId = city.id,
                            date = timeInMillisFromSeconds(responseForecast.dt),
                            temperature = kelvinToCelsius(responseForecast.main.temp),
                            humidity = responseForecast.main.humidity,
                            pressure = responseForecast.main.pressure,
                            clouds = responseForecast.clouds.all,
                            snow = if (responseForecast.snow == null) null else "",
                            weather = responseForecast.weather.first().description,
                            windSpeed = responseForecast.wind.speed,
                            windDirection = responseForecast.wind.deg,
                            iconId = imageIdFromWeather(responseForecast.weather.first().icon),
                            refreshingDate = refreshingDate
                    ))
        }
        return forecasts
    }

}