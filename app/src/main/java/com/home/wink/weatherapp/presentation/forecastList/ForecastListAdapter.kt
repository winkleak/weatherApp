package com.home.wink.weatherapp.presentation.forecastList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ForecastListAdapter: ListAdapter<Forecast, ForecastListAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Forecast>() {

            override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast) =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast) =
                    oldItem == newItem
        }

        val titleDateFormat = SimpleDateFormat("d MMM hh:mm aaa")
        private val temperatureFormat = DecimalFormat("#.#")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(item: Forecast) {
            itemView.apply {
                temperatureTv.text = "${temperatureFormat.format(item.temperature.dec())}°"
                humidityTv.text = item.humidity.toString()
                pressureTv.text = item.pressure.toString()
                windTv.text = "${item.windDirection} ${item.windSpeed} m/s"
                dateTv.text = titleDateFormat.format(Date(item.date))
                weatherIcon.setImageResource(item.iconId)
                weatherDescTv.text = item.weather
            }
        }

    }
}