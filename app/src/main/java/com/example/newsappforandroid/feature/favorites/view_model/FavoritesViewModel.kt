package com.example.newsappforandroid.feature.favorites.view_model

import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.feature.favorites.repository.IFavoritesRepository
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val service: IFavoritesRepository) :
    BaseViewModel() {
    override fun initialize() {

    }
}