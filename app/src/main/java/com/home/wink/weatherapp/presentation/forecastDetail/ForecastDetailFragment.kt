package com.home.wink.weatherapp.presentation.forecastDetail

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.entity.WindDirection.Companion.directionByDegree
import com.home.wink.weatherapp.presentation.base.FragmentWithToolbar
import com.home.wink.weatherapp.presentation.aac.MainViewModelFactory
import com.home.wink.weatherapp.presentation.aac.ToolbarBackButtonViewModel
import com.home.wink.weatherapp.utils.ONE_AFTER_DOT_FORMAT
import com.home.wink.weatherapp.utils.TITLE_DATE_FORMAT
import kotlinx.android.synthetic.main.element_toolbar.*
import kotlinx.android.synthetic.main.fragment_forecast_detail.*
import java.util.*
import javax.inject.Inject

class ForecastDetailFragment : FragmentWithToolbar() {

    override val layoutRes: Int = R.layout.fragment_forecast_detail
    override fun getOptionalToolbar(): Toolbar? = toolbar

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val toolbarBackButtonViewModel by lazy {
         ViewModelProviders.of(this, viewModelFactory).get(ToolbarBackButtonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val forecast = it.getParcelable<Forecast>(FORECAST_PARCELABLE_EXTRAS)
            if(forecast != null){
                val temperature = ONE_AFTER_DOT_FORMAT.format(forecast.temperature.dec())
                temperatureTv.text = getString(R.string.celsius, temperature)
                humidityTv.text =  getString(R.string.percent, forecast.humidity)
                pressureTv.text = forecast.pressure.toString()
                windSpeedTv.text = getString(R.string.wind_speed_value, ONE_AFTER_DOT_FORMAT.format(forecast.windSpeed))
                windDirectionTv.text = "${directionByDegree((forecast.windDirection))}"
                dateTv.text = TITLE_DATE_FORMAT.format(Date(forecast.date))
                weatherIcon.setImageResource(forecast.iconId)
                weatherDescTv.text = forecast.weather
                toolbar.title = forecast.city
                cloudyTv.text = getString(R.string.percent, forecast.clouds)
            }
        }
    }

    override fun onToolbarAttached(toolbar: Toolbar?) {
        super.onToolbarAttached(toolbar)
        toolbarBackButtonViewModel.setToolbarBackButtonVisibility(true)
    }

    companion object {
        const val TAG = "ForecastDetailFragment"
        private const val FORECAST_PARCELABLE_EXTRAS = "forecast_parcelable_extras"
        private const val CITY_NAME_EXTRAS = "city_name_extras"
        fun newInstance(forecasts: Parcelable, cityName: String) = ForecastDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FORECAST_PARCELABLE_EXTRAS, forecasts)
                putString(CITY_NAME_EXTRAS, cityName)
            }
        }
    }
}