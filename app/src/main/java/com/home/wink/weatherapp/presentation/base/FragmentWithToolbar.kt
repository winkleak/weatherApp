package com.home.wink.weatherapp.presentation.base

import android.content.Context
import androidx.appcompat.widget.Toolbar


abstract class FragmentWithToolbar : BaseFragment() {

    private var toolbarAttacher: ToolbarAttacher? = null
    protected abstract fun getOptionalToolbar(): Toolbar?

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            toolbarAttacher = context as ToolbarAttacher
        } catch (e: ClassCastException) {
            throw IllegalArgumentException(" Activity for " + javaClass.name + " should implement ToolbarAttacher")
        }
    }


    override fun onStart() {
        super.onStart()
        onBeforeAttachToolbar(getOptionalToolbar())
        toolbarAttacher?.attach(getOptionalToolbar())
        onToolbarAttached(getOptionalToolbar())
    }

    override fun onStop() {
        super.onStop()
        toolbarAttacher?.detach()
    }

    protected open fun onBeforeAttachToolbar(toolbar: Toolbar?) {}

    protected open fun onToolbarAttached(toolbar: Toolbar?) {}


}