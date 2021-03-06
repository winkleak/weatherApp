package com.home.wink.weatherapp.utils

import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.data.network.response.ForecastNetworkModel
import com.home.wink.weatherapp.data.storage.ForecastModelDb
import com.home.wink.weatherapp.domain.entity.Forecast
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

val TITLE_DATE_FORMAT = SimpleDateFormat("d MMM hh:mm aaa")
val ONE_AFTER_DOT_FORMAT = DecimalFormat("#.#")
fun List<ForecastModelDb>.toForecasts(): List<Forecast> {
    val forecasts: MutableList<Forecast> = mutableListOf()

    this.forEach { forecastDb ->
        forecasts.add(
                Forecast(
                        city = forecastDb.city,
                        cityId = forecastDb.cityId,
                        date = forecastDb.date,
                        temperature = forecastDb.temperature,
                        humidity = forecastDb.humidity,
                        pressure = forecastDb.pressure,
                        clouds = forecastDb.clouds,
                        snow = forecastDb.city,
                        weather = forecastDb.weather,
                        windSpeed = forecastDb.windSpeed,
                        windDirection = forecastDb.windDirection,
                        iconId = forecastDb.iconId
                ))
    }
    return forecasts
}

fun ForecastNetworkModel.toForecastsDbModel(): List<ForecastModelDb> {
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

fun imageIdFromWeather(weatherId: String): Int {
    return when (weatherId) {
        "01d" -> R.drawable.clear_sky
        "01n" -> R.drawable.clear_sky_night
        "02d" -> R.drawable.few_cloudy
        "02n" -> R.drawable.few_cloudy_night
        "03d" -> R.drawable.cloudy
        "03n" -> R.drawable.cloudy
        "04d" -> R.drawable.cloudy
        "04n" -> R.drawable.cloudy
        "09d" -> R.drawable.shower_rain
        "09n" -> R.drawable.shower_rain
        "10d" -> R.drawable.rain
        "10n" -> R.drawable.rain_night
        "11d" -> R.drawable.thunder_storm
        "11n" -> R.drawable.thunder_storm
        "13d" -> R.drawable.snow
        "13n" -> R.drawable.snow
        "50d" -> R.drawable.fog
        "50n" -> R.drawable.fog
        else -> R.drawable.clear_sky
    }
}

fun timeInMillisFromSeconds(seconds: Int) = seconds * 1000L

fun kelvinToCelsius(kelvin: Double): Double {
    return kelvin - 273.15
}