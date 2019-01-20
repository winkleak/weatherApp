package com.home.wink.weatherapp.presentation.forecastList

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.entity.ResponseError
import com.home.wink.weatherapp.presentation.base.BaseFragment
import com.home.wink.weatherapp.presentation.viewModel.MainViewModelFactory

import kotlinx.android.synthetic.main.fragment_forecast_list.*
import javax.inject.Inject

class ForecastListFragment : BaseFragment(), ForecastListAdapter.OnForecastClickListener {

    override val layoutRes: Int = R.layout.fragment_forecast_list

    override fun onForecastClick(forecast: Forecast) {
        viewModel.navigateToDetail(forecast)
    }
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val adapter = ForecastListAdapter(this)
    private lateinit var viewModel: ForecastListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastListViewModel::class.java)
        viewModel.forecastLoadedLiveData.observe(this, Observer<List<Forecast>> {
            adapter.submitList(it)
            swiperefresh.isRefreshing = false
        })
        viewModel.errorLiveData.observe(this, Observer<ResponseError> { showError(it) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        swiperefresh.setOnRefreshListener {
            arguments?.let { loadForecastForCity(it.getInt(CITY_ID_EXTRA)) }
        }
        if(savedInstanceState == null){
            arguments?.let { loadForecastForCity(it.getInt(CITY_ID_EXTRA)) }
        }
    }

    private fun loadForecastForCity(cityId: Int) {
        viewModel.loadForecastForCity(cityId)
        swiperefresh.isRefreshing = true
    }

    companion object {
        private const val CITY_ID_EXTRA = "city_id_extra"
        fun newInstance(cityId: Int) = ForecastListFragment().apply {
            arguments = Bundle().apply { putInt(CITY_ID_EXTRA, cityId) }
        }
    }

}