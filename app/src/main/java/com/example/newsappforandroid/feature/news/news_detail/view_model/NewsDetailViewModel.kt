package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : BaseViewModel<FragmentNewsDetailBinding>() {

    private val _args = MutableLiveData<ArticlesModel?>(null)

    val args get() = _args

    fun setArgs(value: ArticlesModel) {
        _args.postValue(value)
    }

}