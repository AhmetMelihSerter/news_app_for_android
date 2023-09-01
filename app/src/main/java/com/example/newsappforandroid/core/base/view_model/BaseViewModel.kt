package com.example.newsappforandroid.core.base.view_model

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.newsappforandroid.product.constants.utils.Event
import com.example.newsappforandroid.product.init.navigation.NavigationCommand

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

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation

    fun navigate(navDirections: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }
}