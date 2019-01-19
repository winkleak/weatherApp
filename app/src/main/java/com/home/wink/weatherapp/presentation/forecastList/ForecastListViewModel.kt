package com.home.wink.weatherapp.presentation.forecastList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.domain.entity.Forecast
import com.home.wink.weatherapp.domain.usecase.GetAllForecastUseCase
import com.home.wink.weatherapp.domain.usecase.base.UseCase

class ForecastListViewModel(val getAllForecastUseCase: GetAllForecastUseCase): ViewModel(), UseCase.Callback<List<Forecast>> {
    val forecastLoadedLiveData: MutableLiveData<List<Forecast>> by lazy {
        MutableLiveData<List<Forecast>>()
    }


/*    val categoriesLiveData: MutableLiveData<CategoriesFetchResult> by lazy {
        MutableLiveData<CategoriesFetchResult>()
    }
    val cityLiveData: MutableLiveData<City> by lazy {
        MutableLiveData<City>()
    }
    val dateLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val disposable = CompositeDisposable()

    fun loadCategories() {
        fetchCategories()
    }

    private fun fetchCategories() {
        disposable.add(api.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { response ->
                            categoriesLiveData.postValue(CategoriesFetchResult.success(response.data.categories))
                        },
                        { error ->
                            categoriesLiveData.postValue(CategoriesFetchResult.error(error))
                        }))

        disposable.add(deliveryRepository.getDeliveryCity
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    cityLiveData.postValue(result)
                })
        disposable.add(deliveryRepository.getDeliveryDate
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    dateLiveData.postValue(result)
                })
    }*/

    override fun onSuccess(response: List<Forecast>) {
       forecastLoadedLiveData.postValue(response)
    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCleared() {
        super.onCleared()
        //disposable.clear()
    }

    fun loadForecastForCity(cityId: Int) {
        getAllForecastUseCase.execute(cityId, this)
    }

}
