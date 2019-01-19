package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.extensions.setLaunchScreen
import com.home.wink.weatherapp.navigation.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator: Navigator =
            object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
                override fun setupFragmentTransaction(
                        command: Command?,
                        currentFragment: Fragment?,
                        nextFragment: Fragment?,
                        fragmentTransaction: FragmentTransaction
                ) {
                    //fix incorrect order lifecycle callback of MainFlowFragment
                    fragmentTransaction.setReorderingAllowed(true)
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        App.appComponent.inject(this)
        navigator.setLaunchScreen(Screens.MainFlow)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }


    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

}
