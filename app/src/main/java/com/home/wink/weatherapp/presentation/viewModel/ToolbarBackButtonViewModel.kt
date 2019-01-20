package com.home.wink.weatherapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolbarBackButtonViewModel : ViewModel() {

    private val showToolbarBackButtonMutable: MutableLiveData<Boolean> = MutableLiveData()
    val showToolbarBackButton: LiveData<Boolean> = showToolbarBackButtonMutable

    fun setToolbarBackButtonVisibility(isVisible: Boolean) {
        showToolbarBackButtonMutable.value = isVisible
    }
}