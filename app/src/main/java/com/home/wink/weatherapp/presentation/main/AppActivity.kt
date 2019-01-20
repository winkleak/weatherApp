package com.home.wink.weatherapp.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.home.wink.weatherapp.App
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.navigation.Screens
import com.home.wink.weatherapp.presentation.base.BaseActivity
import com.home.wink.weatherapp.presentation.base.FragmentWithToolbar
import com.home.wink.weatherapp.presentation.viewModel.MainViewModelFactory
import com.home.wink.weatherapp.presentation.viewModel.ToolbarBackButtonViewModel
import com.home.wink.weatherapp.utils.nonNullObserve
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class AppActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val toolbarBackButtonViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ToolbarBackButtonViewModel::class.java)
    }

    private val currentFragmentWithToolbar: FragmentWithToolbar?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? FragmentWithToolbar

    private val navigator: Navigator =
            object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
                override fun setupFragmentTransaction(
                        command: Command?,
                        currentFragment: Fragment?,
                        nextFragment: Fragment?,
                        fragmentTransaction: FragmentTransaction
                ) {
                    //fix incorrect order lifecycle callback of MainFragment
                    fragmentTransaction.setReorderingAllowed(true)
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        App.appComponent.inject(this)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.MainFlow)))
        }
        observeData()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun observeData() {
        toolbarBackButtonViewModel.showToolbarBackButton.nonNullObserve(this) { isVisible ->
            supportActionBar?.run {
                setDisplayShowHomeEnabled(isVisible)
                setDisplayHomeAsUpEnabled(isVisible)
            }
        }
    }
}
