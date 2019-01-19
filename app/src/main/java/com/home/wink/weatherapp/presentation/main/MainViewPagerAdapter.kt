package com.home.wink.weatherapp.presentation.main

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.home.wink.weatherapp.presentation.forecastList.ForecastListFragment

const val MUNICH = 3220838
const val MOSCOW = 524901

class MainViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = listOf(
            ForecastListFragment.newInstance(MUNICH),
            ForecastListFragment.newInstance(MOSCOW)
    )
    override fun getItem(position: Int): BaseFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}