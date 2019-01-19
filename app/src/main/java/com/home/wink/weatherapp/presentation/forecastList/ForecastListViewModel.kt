package com.home.wink.weatherapp.presentation.forecastList

import androidx.lifecycle.ViewModel
import com.home.wink.weatherapp.domain.interactor.ForecastInteractor

class ForecastListViewModel(val forecastInteractor: ForecastInteractor): ViewModel() {

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

    override fun onCleared() {
        super.onCleared()
        //disposable.clear()
    }

}
