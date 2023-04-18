package com.example.newsappforandroid.core.base.view_model

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewDataBinding?> : ViewModel() {
    private var _binding: T? = null

    var binding: T
        get() = _binding!!
        set(value) {
            _binding = value
        }

    private var _hideKeyboard: () -> Unit = {}

    open fun initialize(dataViewBinding: T, hideKeyboard: () -> Unit) {
        _binding = dataViewBinding
        _hideKeyboard = hideKeyboard
    }

    fun hideKeyboard() {
        _hideKeyboard.invoke()
    }
}