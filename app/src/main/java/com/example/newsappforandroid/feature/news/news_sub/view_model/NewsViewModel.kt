package com.example.newsappforandroid.feature.news.news_sub.view_model

import ArticleListAdapter
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature._model.SourceModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.service.INewsService
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.PrintWriter
import java.io.StringWriter
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val service: INewsService) :
    BaseViewModel<FragmentNewsBinding>() {

    private val query: String = "messi"

    private val page: Int = 1

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList get() = _articleList

    private val _searchHasFocus = MutableLiveData(false)

    val searchHasFocus get() = _searchHasFocus

     lateinit var articleListAdapter: ArticleListAdapter

    override fun initialize(dataViewBinding: FragmentNewsBinding, hideKeyboard: () -> Unit) {
        super.initialize(dataViewBinding, hideKeyboard)
        binding.recyclerViewNews.adapter = articleListAdapter
        dataViewBinding.searchNewsTextInputEditText.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                _searchHasFocus.value = hasFocus
            }
        _articleList.value = mutableListOf(
            ArticlesModel(
                source = SourceModel(
                    id = "1",
                    name = "name"
                ),
                author = "Melih",
                title = "Title",
                description = "Description",
                url = "url",
                urlToImage = "https://picsum.photos/100",
                publishedAt = "PublishedAt",
                content = "Content"
            )
        )
        articleListAdapter.setList(_articleList.value)
    }

    private fun searchNews() {

        /*viewModelScope.launch(Dispatchers.IO) {
            service.searchNews(
                NewsRequest(
                    q = query,
                    page = page,
                ),
                _articleList,
            )

            withContext(Dispatchers.Main) {
                if (response?.articles != null) {
                    _articleList.postValue(response.articles)
                }
            }
        }*/
    }
}