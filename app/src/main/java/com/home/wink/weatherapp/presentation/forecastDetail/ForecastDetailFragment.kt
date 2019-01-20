package com.home.wink.weatherapp.presentation.forecastDetail

import android.os.Bundle
import android.os.Parcelable
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.directionByDegree
import com.home.wink.weatherapp.presentation.main.BaseFragment
import kotlinx.android.synthetic.main.fragment_forecast_detail.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ForecastDetailFragment: BaseFragment(){
    override val layoutRes: Int = R.layout.fragment_forecast_detail

    companion object {
        const val TAG = "ForecastDetailFragment"
        val titleDateFormat = SimpleDateFormat("d MMM hh:mm aaa")
        private val temperatureFormat = DecimalFormat("#.#")
        private const val FORECAST_PARCELABLE_EXTRAS = "forecast_parcelable_extras"
        fun newInstance(forecastDto: Parcelable) = ForecastDetailFragment().apply {
            arguments = Bundle().apply { putParcelable(FORECAST_PARCELABLE_EXTRAS, forecastDto) }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let { val forecast = it.getParcelable<ForecastDto>(FORECAST_PARCELABLE_EXTRAS)
            temperatureTv.text = "${temperatureFormat.format(forecast.temperature.dec())}°"
            humidityTv.text = "${forecast.humidity}%"
            pressureTv.text = forecast.pressure.toString()
            windSpeedTv.text = "${forecast.windSpeed} m/s"
            windDirectionTv.text = "${directionByDegree(forecast.windDirection)}"
            dateTv.text = titleDateFormat.format(Date(forecast.date))
            weatherIcon.setImageResource(forecast.iconId)
            weatherDescTv.text = forecast.weather
            cityTv.text = forecast.city
            cloudyTv.text = "${forecast.clouds}%"
        }
    }
}