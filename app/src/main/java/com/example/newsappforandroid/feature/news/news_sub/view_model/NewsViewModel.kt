package com.example.newsappforandroid.feature.news.news_sub.view_model

import android.util.Log
import com.example.newsappforandroid.R
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.service.INewsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val service: INewsService) : BaseViewModel<FragmentNewsBinding>() {

    private val query: String = ""

    private val page: Int = 1

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList get() = _articleList

    private val _searchHasFocus = MutableLiveData(false)

    val searchHasFocus get() = _searchHasFocus

    override fun initialize(dataViewBinding: FragmentNewsBinding, hideKeyboard: () -> Unit) {
        super.initialize(dataViewBinding, hideKeyboard)
        dataViewBinding.searchNewsTextInputEditText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            _searchHasFocus.value = hasFocus
        }
    }

    fun searchNews() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = service.searchNews(
                NewsRequest(
                    q = query,
                    page = page,
                ),
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.articles.let {
                        _articleList.postValue(it)
                    }
                }
            }
        }
    }



}