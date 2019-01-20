package com.home.wink.weatherapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.home.wink.weatherapp.presentation.main.AppActivity

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() = false


    protected fun showError(error: String) {
        var toast = (activity as AppActivity).toast
        toast.cancel()
        toast = Toast.makeText(activity, error, Toast.LENGTH_SHORT)
        toast.show()
    }
}