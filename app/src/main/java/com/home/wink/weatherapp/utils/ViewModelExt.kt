package com.home.wink.weatherapp.utils

import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.R
import com.home.wink.weatherapp.data.network.response.error.NoInternetException
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun ViewModel.getCommonErrorDescription(e: Throwable): Int = when (e) {
    is HttpException -> R.string.error_api
    is NoInternetException -> R.string.error_no_internet
    is SocketTimeoutException -> R.string.error_no_internet
    else -> R.string.error_unknown
}