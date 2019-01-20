package com.home.wink.weatherapp.presentation.forecastDetail

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.directionByDegree
import com.home.wink.weatherapp.presentation.base.FragmentWithToolbar
import com.home.wink.weatherapp.presentation.viewModel.MainViewModelFactory
import com.home.wink.weatherapp.presentation.viewModel.ToolbarBackButtonViewModel
import kotlinx.android.synthetic.main.element_toolbar.*
import kotlinx.android.synthetic.main.fragment_forecast_detail.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ForecastDetailFragment: FragmentWithToolbar(){

    override fun getOptionalToolbar(): Toolbar? = toolbar

    override val layoutRes: Int = R.layout.fragment_forecast_detail

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val toolbarBackButtonViewModel by lazy {
        activity?.run { ViewModelProviders.of(this, viewModelFactory).get(ToolbarBackButtonViewModel::class.java) }
                ?: throw IllegalArgumentException("Activity is needed to create ToolbarBackButtonViewModel")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
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
            toolbar.title = forecast.city
            cloudyTv.text = "${forecast.clouds}%"
        }
    }

    override fun onToolbarAttached(toolbar: Toolbar?) {
        super.onToolbarAttached(toolbar)
        toolbarBackButtonViewModel.setToolbarBackButtonVisibility(true)
    }

    companion object {
        const val TAG = "ForecastDetailFragment"
        val titleDateFormat = SimpleDateFormat("d MMM hh:mm aaa")
        private val temperatureFormat = DecimalFormat("#.#")
        private const val FORECAST_PARCELABLE_EXTRAS = "forecast_parcelable_extras"
        private const val CITY_NAME_EXTRAS = "city_name_extras"
        fun newInstance(forecastDto: Parcelable, cityName: String) = ForecastDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FORECAST_PARCELABLE_EXTRAS, forecastDto)
                putString(CITY_NAME_EXTRAS, cityName)
            }
        }
    }
}