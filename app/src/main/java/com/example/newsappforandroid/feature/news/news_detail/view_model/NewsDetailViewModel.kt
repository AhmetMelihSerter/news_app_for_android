package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.MutableLiveData
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : BaseViewModel<FragmentNewsDetailBinding>() {

    private val _args = MutableLiveData<ArticlesModel?>(null)

    val args get() = _args

    fun setArgs(value: ArticlesModel) {
        _args.postValue(value)
    }

}
