package com.home.wink.weatherapp.presentation.forecastList

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.presentation.main.BaseFragment
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.presentation.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_forecast_list.*
import javax.inject.Inject

class ForecastListFragment : BaseFragment() {

    companion object {
        private const val CITY_ID_EXTRA = "city_id_extra"
        fun newInstance(cityId: Int) = ForecastListFragment().apply {
            arguments = Bundle().apply { putInt(CITY_ID_EXTRA, cityId) }
        }
    }

    override val layoutRes: Int = R.layout.fragment_forecast_list
    private val adapter = ForecastListAdapter()

    private lateinit var viewModel: ForecastListViewModel
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.forecastLoadedLiveData.observe(this, Observer<List<Forecast>> {
            adapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        arguments?.let { viewModel.loadForecastForCity(it.getInt(CITY_ID_EXTRA)) }
    }
}