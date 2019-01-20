package com.home.wink.weatherapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.wink.weatherapp.domain.entity.ResponseError

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() = false


    fun showError(error: ResponseError) {
        //TODO show toast or snackbar
    }
}