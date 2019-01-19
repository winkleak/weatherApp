package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.home.wink.weatherapp.R
import kotlinx.android.synthetic.main.main_flow_layout.*

class MainFlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.main_flow_layout

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.firstCity -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.secondCity -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}