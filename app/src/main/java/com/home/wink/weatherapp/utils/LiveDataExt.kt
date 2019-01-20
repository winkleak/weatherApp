package com.home.wink.weatherapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.nonNullObserve(lifecycleOwner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(lifecycleOwner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}
