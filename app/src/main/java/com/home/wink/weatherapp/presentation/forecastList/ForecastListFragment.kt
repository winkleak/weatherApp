package com.home.wink.weatherapp.presentation.forecastList

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.presentation.main.BaseFragment
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.presentation.MainViewModelFactory
import javax.inject.Inject

class ForecastListFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_forecast_list

    private lateinit var viewModel: ForecastListViewModel
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastListViewModel::class.java)
        viewModel.loadForecastForCity(524901)
       // viewModel.loadCategories()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.forecastLoadedLiveData.observe(this, Observer<List<Forecast>> {
            Log.d("forecastsLoaded", it.toString())

        })
    /*    viewModel.categoriesLiveData.observe(this, Observer<CategoriesFetchResult> {
            processResult(it)

        })
        viewModel.cityLiveData.observe(this, Observer<City> {
            deliveryCityTv.text = it.name
        })
        viewModel.dateLiveData.observe(this, Observer<String> {
            deliveryDateTv.text = it
        })*/
    }
}