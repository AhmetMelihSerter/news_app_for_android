package com.example.newsappforandroid.feature.news.news_sub.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.repository.INewsRepository
import com.example.newsappforandroid.feature.news.news_sub.view.NewsFragmentDirections
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: INewsRepository) :
    BaseViewModel() {

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList get(): LiveData<List<ArticlesModel>> = _articleList

    private var searchJob: Job = Job()

    private var textChangeCountDownJob: Job = Job()

    private var _newsRequest: NewsRequest? = null

    override fun initialize() {

    }

    fun adapterItemOnClick(model: ArticlesModel) {
        navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(model))
    }

    fun setSearchNewsText(text: String) {
        if(_newsRequest?.q == text) {
            return
        }
        textChangeCountDownJob.cancel()
        textChangeCountDownJob = viewModelScope.launch {
            delay(500)
            validateSearchNews(text)
        }
    }

    fun searchNewsOnClear() {
        searchJob.cancel()
        hideKeyboard()
        _articleList.value = emptyList()
    }

    private fun validateSearchNews(query: String) {
        if (query.isEmpty()) {
            return
        }
        val page = if(_newsRequest?.q == query) (_newsRequest?.page ?: 0) + 1 else 1
        _newsRequest = NewsRequest(
            q = query,
            page = page
        )
        searchNews()
    }

    private fun searchNews() {
        Logger.i("Trigger searchNews")

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchNews(_newsRequest!!)
            withContext(Dispatchers.Main) {
                val tempList = response?.articles?.take(8).orEmpty()
                _articleList.postValue(tempList)
            }
        }
    }
}