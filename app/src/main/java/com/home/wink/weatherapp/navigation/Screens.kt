package com.home.wink.weatherapp.navigation

import com.home.wink.weatherapp.presentation.main.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object MainFlow : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }
}