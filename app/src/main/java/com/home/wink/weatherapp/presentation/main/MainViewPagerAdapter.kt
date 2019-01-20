package com.home.wink.weatherapp.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.home.wink.weatherapp.presentation.forecastList.ForecastListFragment


class MainViewPagerAdapter(manager: FragmentManager, private val fragments: List<ForecastListFragment>) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}