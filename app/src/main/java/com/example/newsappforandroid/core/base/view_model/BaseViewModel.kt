package com.example.newsappforandroid.core.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.newsappforandroid.product.constants.navigation.NavigationCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _keyboardStatus = Channel<Boolean>()

    val keyboardStatus get() = _keyboardStatus.receiveAsFlow()

    fun hideKeyboard() {
        viewModelScope.launch {
            _keyboardStatus.send(true)
        }
    }

    private val _navigation = Channel<NavigationCommand>()

    val navigation get() = _navigation.receiveAsFlow()

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

    abstract fun initialize()
}