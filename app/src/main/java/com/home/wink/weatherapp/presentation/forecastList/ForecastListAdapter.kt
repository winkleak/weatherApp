package com.home.wink.weatherapp.presentation.forecastList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.entity.directionByDegree
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ForecastListAdapter(private val listener: OnForecastClickListener) : ListAdapter<Forecast, ForecastListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(itemView: View, private val listener: OnForecastClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onForecastClick(getItem(adapterPosition))
        }

        fun bindData(item: Forecast) {
            itemView.apply {
                temperatureTv.text = "${temperatureFormat.format(item.temperature.dec())}°"
                humidityTv.text = item.humidity.toString()
                pressureTv.text = item.pressure.toString()
                windTv.text = "${directionByDegree(item.windDirection)} ${item.windSpeed} m/s"
                dateTv.text = titleDateFormat.format(Date(item.date))
                weatherIcon.setImageResource(item.iconId)
                weatherDescTv.text = item.weather
            }
        }

    }

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

    interface OnForecastClickListener {
        fun onForecastClick(forecast: Forecast)
    }
}