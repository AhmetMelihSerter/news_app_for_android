package com.example.newsappforandroid.feature.favorites.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.feature.favorites.repository.IFavoritesRepository
import com.example.newsappforandroid.feature.news.news_sub.view.NewsFragmentDirections
import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: IFavoritesRepository) :
    BaseViewModel() {

    private val _favoritesList = MutableLiveData<List<FavoritesEntity>>()

    val favoritesList get(): LiveData<List<FavoritesEntity>> = _favoritesList

    override fun initialize() {
        getAllFavorite()
    }

    fun adapterItemOnClick(model: FavoritesEntity) {
        navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(model))
    }

    private fun getAllFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.gelAllFavorite()

            withContext(Dispatchers.Main) {
                _favoritesList.postValue(result)
            }
        }
    }
}