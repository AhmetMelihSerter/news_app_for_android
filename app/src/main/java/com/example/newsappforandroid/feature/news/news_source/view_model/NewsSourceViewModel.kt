package com.example.newsappforandroid.feature.news.news_source.view_model

import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor() : BaseViewModel() {

    private lateinit var _url: String

    fun setArgs(value: String) {
        _url = value
    }

    private val _loadUrl = Channel<String>()

    val loadUrl get() = _loadUrl.receiveAsFlow()

    override fun initialize() {
        viewModelScope.launch {
            _loadUrl.send(_url)
        }
    }

}