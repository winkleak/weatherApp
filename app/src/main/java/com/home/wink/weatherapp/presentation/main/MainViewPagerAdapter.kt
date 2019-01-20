package com.home.wink.weatherapp.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.home.wink.weatherapp.presentation.forecastList.ForecastListFragment
import java.lang.AssertionError

private const val MUNCHEN = 3220838
private const val MOSCOW = 524901
private const val PAGE_COUNT = 2
class MainViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {


    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ForecastListFragment.newInstance(MUNCHEN)
            1 -> ForecastListFragment.newInstance(MOSCOW)
            else -> throw AssertionError("incorrect viewpager position")
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}