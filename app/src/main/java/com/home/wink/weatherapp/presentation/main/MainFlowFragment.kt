package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.presentation.forecastList.ForecastListFragment
import kotlinx.android.synthetic.main.main_flow_layout.*

const val MUNICH = 3220838
const val MOSCOW = 524901

class MainFlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.main_flow_layout
    private lateinit var pagerAdapter: MainViewPagerAdapter
    private val fragments = listOf(
            ForecastListFragment.newInstance(MUNICH),
            ForecastListFragment.newInstance(MOSCOW)
    )

    private val onTabSelectedListener = AHBottomNavigation.OnTabSelectedListener{position, _ ->
        viewPager.currentItem = position
        return@OnTabSelectedListener true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pagerAdapter = MainViewPagerAdapter(childFragmentManager, fragments)
        viewPager.adapter = pagerAdapter
        navigation.addItem(AHBottomNavigationItem(getString(R.string.munich), R.drawable.ic_home_black_24dp))
        navigation.addItem(AHBottomNavigationItem(getString(R.string.moscow), R.drawable.ic_home_black_24dp))
        navigation.setOnTabSelectedListener(onTabSelectedListener)
        navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                navigation.currentItem = position
            }
        })
    }

    companion object {
        const val TAG = "MainFlowFragment"
    }

}