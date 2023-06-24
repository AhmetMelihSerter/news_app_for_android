package com.example.newsappforandroid.feature.news.news_sub.view_model

import ArticleListAdapter
import android.view.View.OnFocusChangeListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.service.INewsService
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val service: INewsService) :
    BaseViewModel<FragmentNewsBinding>() {

    private val query: String = "Beşiktaş"

    private val page: Int = 1

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList get() = _articleList

    private val _searchHasFocus = MutableLiveData(false)

    val searchHasFocus get() = _searchHasFocus

    lateinit var articleListAdapter: ArticleListAdapter

    override fun initialize(dataViewBinding: FragmentNewsBinding, hideKeyboard: () -> Unit) {
        super.initialize(dataViewBinding, hideKeyboard)
        binding.recyclerViewNews.adapter = articleListAdapter
        searchEditTextListenerFocus()
        searchEditTextListenerText()
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
            flow<Unit> { }.debounce(500).onEach {
                searchNews()
            }.launchIn(viewModelScope)
        }
    }

    private fun searchNews() {
        Logger.i("Job Running")
        viewModelScope.launch(Dispatchers.IO) {
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