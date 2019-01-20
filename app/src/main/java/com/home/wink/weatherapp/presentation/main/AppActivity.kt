package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R

class AppActivity : AppCompatActivity() {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        App.appComponent.inject(this)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(MainFlowFragment.TAG)
        if(fragment == null){
            transaction.add(R.id.container, MainFlowFragment(), MainFlowFragment.TAG).commit()
        }
    }
}
