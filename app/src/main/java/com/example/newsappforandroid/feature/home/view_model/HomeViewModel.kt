package com.example.newsappforandroid.feature.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.home.model.HomeRequest
import com.example.newsappforandroid.feature.home.service.IHomeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val service: IHomeService) : ViewModel() {

    private val query: String = ""

    private val page: Int = 1

    private val _articleList = MutableLiveData<List<ArticlesModel>>()

    val articleList : LiveData<List<ArticlesModel>> get() = _articleList

    fun searchNews() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = service.searchNews(
                HomeRequest(
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