package com.home.wink.weatherapp.domain.usecase.base

interface UseCase<P, R> {

    interface Callback<R> {
        fun onSuccess(response: R)
        fun onError(throwable: Throwable)
    }

    fun execute(parameter: P, callback: Callback<R>)
}