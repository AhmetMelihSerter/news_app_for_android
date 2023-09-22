package com.example.newsappforandroid.feature.news.news_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.feature.news.news_detail.repository.INewsDetailRepository
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val repository: INewsDetailRepository) :
    BaseViewModel() {

    private val _args = MutableLiveData<ArticlesModel>(null)

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

    private val _isFavorite = MutableLiveData(false)

    val isFavorite get() : LiveData<Boolean> = _isFavorite

    private fun checkFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = repository.checkFavorites(_args.value!!.title)

            withContext(Dispatchers.Main) {
                _isFavorite.postValue(isFavorite)
            }
        }
    }

    fun addOrRemoveFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = _isFavorite.value!!
            if (isFavorite) repository.addFavorite(_args.value!!)
            else repository.deleteFavorite(_args.value!!)
            withContext(Dispatchers.Main) {
                _isFavorite.postValue(!isFavorite)
            }
        }
    }
}
