package com.home.wink.weatherapp.presentation.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity(), ToolbarAttacher {

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun attach(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
    }

    override fun detach() {
        setSupportActionBar(null)
    }
}
