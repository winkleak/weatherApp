package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R

import com.home.wink.weatherapp.presentation.base.FragmentWithToolbar
import kotlinx.android.synthetic.main.element_toolbar.*
import kotlinx.android.synthetic.main.fragment_main_layout.*

class MainFragment : FragmentWithToolbar() {

    override val layoutRes: Int = R.layout.fragment_main_layout
    override fun getOptionalToolbar(): Toolbar? = toolbar
    private lateinit var pagerAdapter: MainViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    private val onTabSelectedListener = AHBottomNavigation.OnTabSelectedListener { position, _ ->
        toolbar.title = if (position == 0) getString(R.string.munchen) else getString(R.string.moscow)
        viewPager.currentItem = position
        return@OnTabSelectedListener true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = MainViewPagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                navigation.currentItem = position
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigation.addItem(AHBottomNavigationItem(getString(R.string.munchen), R.drawable.ic_home_black_24dp))
        navigation.addItem(AHBottomNavigationItem(getString(R.string.moscow), R.drawable.ic_home_black_24dp))
        navigation.setOnTabSelectedListener(onTabSelectedListener)
        navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
    }

    override fun onBeforeAttachToolbar(toolbar: Toolbar?) {
        super.onBeforeAttachToolbar(toolbar)
        toolbar?.title = navigation.getItem(navigation.currentItem).getTitle(activity)
    }


    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

}