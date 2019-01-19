package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.home.wink.weatherapp.R
import kotlinx.android.synthetic.main.main_flow_layout.*


class MainFlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.main_flow_layout
    private lateinit var pagerAdapter: MainViewPagerAdapter


    private val onTabSelectedListener = AHBottomNavigation.OnTabSelectedListener{position, _ ->
        viewPager.currentItem = position
        return@OnTabSelectedListener true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pagerAdapter = MainViewPagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        navigation.addItem(AHBottomNavigationItem(getString(R.string.munich), R.drawable.ic_home_black_24dp))
        navigation.addItem(AHBottomNavigationItem(getString(R.string.moscow), R.drawable.ic_home_black_24dp))
        navigation.setOnTabSelectedListener(onTabSelectedListener)
        navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
    }
}