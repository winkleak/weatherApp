package com.home.wink.weatherapp.presentation.base

import androidx.appcompat.widget.Toolbar

interface ToolbarAttacher {

    fun attach(toolbar: Toolbar?)

    fun detach()
}
