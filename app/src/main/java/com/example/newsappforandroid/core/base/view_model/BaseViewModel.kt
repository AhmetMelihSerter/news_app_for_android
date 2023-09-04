package com.example.newsappforandroid.core.base.view_model

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.newsappforandroid.product.constants.commands.NavigationCommand
import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : ViewDataBinding?> : ViewModel() {
    private var _binding: T? = null

    var binding: T
        get() = _binding!!
        set(value) {
            _binding = value
        }

    private var _hideKeyboardCallback: (() -> Unit)? = null

    fun setBindingAndKeyboardCallback(dataViewBinding: T, value: () -> Unit) {
        _binding = dataViewBinding
        _hideKeyboardCallback = value
    }

    private val _navigation = Channel<NavigationCommand>()

    val navigation = _navigation.receiveAsFlow()

    fun navigate(navDirections: NavDirections) {
        viewModelScope.launch {
            _navigation.send(NavigationCommand.ToDirection(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigation.send(NavigationCommand.Back)
        }
    }

    fun hideKeyboard() {
        _hideKeyboardCallback?.invoke()
    }

    abstract fun initialize()
}