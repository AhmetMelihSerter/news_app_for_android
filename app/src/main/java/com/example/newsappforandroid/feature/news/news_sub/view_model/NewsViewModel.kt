package com.example.newsappforandroid.feature.news.news_sub.view_model

import ArticleListAdapter
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.service.INewsService
import com.example.newsappforandroid.feature.news.news_sub.view.NewsFragmentDirections
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val service: INewsService) :
    BaseViewModel<FragmentNewsBinding>() {

    private val page: Int = 1

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList get() = _articleList

    private val _searchHasFocus = MutableLiveData(false)

    val searchHasFocus get() = _searchHasFocus

    lateinit var articleListAdapter: ArticleListAdapter

    private var searchJob: Job = Job()

    private var textChangeCountDownJob: Job = Job()

    override fun initialize(dataViewBinding: FragmentNewsBinding, hideKeyboard: () -> Unit) {
        super.initialize(dataViewBinding, hideKeyboard)
        binding.recyclerViewNews.adapter = articleListAdapter
        articleListAdapter.onItemClickListener = onClickAdapter
        searchEditTextListenerFocus()
        searchEditTextListenerText()
        searchEditTextListenerOnClick()
    }

    private val onClickAdapter: (it: ArticlesModel) -> Unit = {
        navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(it))
    }

    fun goToSecondFragmentClicked() {
        //navigate(NewsDetailFragmentDirections())
    }

    fun goToSecondFragmentWithArgs() {
        /*navigate(
            NewsDetailFragmentDirections.actionFirstSceneFragmentToSecondSceneFragment(
                userId = "Test user id"
            )
        )*/
    }

    private fun updateArticleList(model: List<ArticlesModel>) {
        _articleList.postValue(model)
        articleListAdapter.postValue(model)
    }

    private fun searchEditTextListenerFocus() {
        binding.searchNewsTextInputEditText.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                _searchHasFocus.value = hasFocus
            }
    }

    private fun searchEditTextListenerText() {
        binding.searchNewsTextInputEditText.doAfterTextChanged {
            textChangeCountDownJob.cancel()
            textChangeCountDownJob = viewModelScope.launch {
                delay(500)
                validateSearchNews(it.toString())
            }
        }
    }

    private fun searchEditTextListenerOnClick() {
        binding.searchNewsInputLayout.setEndIconOnClickListener {
            searchTextOnClickClear()
        }
    }

    private fun searchTextOnClickClear() {
        searchJob.cancel()
        hideKeyboard()
        searchTextClearAndUnFocus()
        updateArticleList(arrayListOf())
    }

    private fun searchTextClearAndUnFocus() {
        binding.searchNewsTextInputEditText.text?.clear()
        binding.searchNewsInputLayout.clearFocus()
    }

    private fun validateSearchNews(query: String) {
        if(query.isEmpty()){
            return
        }

        searchNews(query)
    }

    private fun searchNews(query: String) {
        Logger.i("SearchNews Running")

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val responseModel = service.searchNews(
                NewsRequest(
                    q = query,
                    page = page,
                )
            )
            withContext(Dispatchers.Main) {
                updateArticleList(responseModel?.articles ?: emptyList())
            }
        }
    }
}