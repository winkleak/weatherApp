package com.home.wink.weatherapp.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by winkleak on 21.01.2019
 */

fun Disposable?.addTo(compositeDisposable: CompositeDisposable) {
    this?.let { compositeDisposable.add(it) }
}

fun Disposable?.deleteFrom(compositeDisposable: CompositeDisposable) {
    this?.let { compositeDisposable.delete(it) }
}