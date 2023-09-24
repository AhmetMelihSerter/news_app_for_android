package com.example.newsappforandroid.feature.favorites.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.feature.favorites.repository.IFavoritesRepository
import com.example.newsappforandroid.feature.favorites.view.FavoritesFragmentDirections
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: IFavoritesRepository) :
    BaseViewModel() {

    private val _favoritesList = MutableLiveData<List<ArticlesModel>>()

    val favoritesList get(): LiveData<List<ArticlesModel>> = _favoritesList

    override fun initialize() {
        getAllFavorite()
    }

    fun adapterItemOnClick(model: ArticlesModel) {
        navigate(FavoritesFragmentDirections.actionFavoritesFragmentToNewsDetailFragment(model))
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