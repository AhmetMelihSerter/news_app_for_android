package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : BaseViewModel<FragmentNewsDetailBinding>() {

    private val _args = MutableLiveData<ArticlesModel?>(null)

    val args get() = _args

    fun setArgs(value: ArticlesModel) {
        _args.postValue(value)
    }

    override fun initialize() {
        checkFavorite()
    }

    private val _shareDataChannel = Channel<String>()

    val shareDataChannel = _shareDataChannel.receiveAsFlow()

    fun shareUrl() {
        if (_args.value?.url == null) {
            return
        }
        viewModelScope.launch {
            _shareDataChannel.send(_args.value!!.url)
        }
    }

    private val _isFavorite = MutableLiveData(true)

    private val _isFavoriteChannel = Channel<Boolean>()

    val isFavoriteChannel = _isFavoriteChannel.receiveAsFlow()

    private fun checkFavorite() {
        viewModelScope.launch {
            _isFavorite.postValue(!(_isFavorite.value!!))
            _isFavoriteChannel.send(_isFavorite.value!!)
        }
    }

    fun addFavorite() {
        checkFavorite()
    }
}
