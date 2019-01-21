package com.home.wink.weatherapp.utils

import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun ViewModel.getCommonErrorDescription(e: Throwable): Int = when (e) {
    is HttpException -> R.string.error_api
    is IOException -> R.string.error_no_internet
    is SocketTimeoutException -> R.string.error_no_internet
    else -> R.string.error_unknown
}