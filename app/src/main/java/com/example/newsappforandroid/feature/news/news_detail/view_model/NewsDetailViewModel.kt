package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.feature.news.news_detail.repository.INewsDetailRepository
import com.example.newsappforandroid.feature.news.news_detail.view.NewsDetailFragmentDirections
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val repository: INewsDetailRepository) :
    BaseViewModel() {

    private lateinit var _args: ArticlesModel

    val args get() = _args

    fun setArgs(value: ArticlesModel) {
        _args = value
    }

    override fun initialize() {
        checkFavorite()
    }

    fun navigateNewsSource() {
        navigate(NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsSourceFragment(_args.url))
    }

    private val _shareData = Channel<String>()

    val shareData get() = _shareData.receiveAsFlow()

    fun shareUrl() {
        viewModelScope.launch {
            _shareData.send(_args.url)
        }
    }

    private val _isFavorite = MutableLiveData(false)

    val isFavorite get() : LiveData<Boolean> = _isFavorite

    private fun checkFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = repository.checkFavorites(_args.title)

            withContext(Dispatchers.Main) {
                _isFavorite.postValue(isFavorite)
            }
        }
    }

    fun addOrRemoveFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = _isFavorite.value!!
            if (isFavorite) repository.deleteFavorite(_args)
            else repository.addFavorite(_args)
            withContext(Dispatchers.Main) {
                _isFavorite.postValue(!isFavorite)
            }
        }
    }
}
