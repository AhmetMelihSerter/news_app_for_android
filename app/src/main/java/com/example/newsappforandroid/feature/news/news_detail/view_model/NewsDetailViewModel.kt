package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : BaseViewModel() {

    private val _args = MutableLiveData<ArticlesModel?>(null)

    val args get() = _args

    fun setArgs(value: ArticlesModel) {
        _args.value = value
    }

    override fun initialize() {
        checkFavorite()
    }

    private val _shareData = MutableLiveData("")

    val shareData get() : LiveData<String> = _shareData

    fun shareUrl() {
        if (_args.value?.url == null) {
            return
        }
        _shareData.value = _args.value!!.url
    }

    private val _isFavorite = MutableLiveData(true)

    val isFavorite get() : LiveData<Boolean> = _isFavorite

    private fun checkFavorite() {
        _isFavorite.value = !(_isFavorite.value!!)
    }

    fun addFavorite() {
        checkFavorite()
    }
}
