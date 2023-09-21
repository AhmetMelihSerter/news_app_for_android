package com.example.newsappforandroid.feature.favorites.repository

import com.example.newsappforandroid.product.model.ArticlesModel

interface IFavoritesRepository {
    suspend fun gelAllFavorite() : List<ArticlesModel>

    suspend fun addFavorite(model: ArticlesModel)

    suspend fun deleteFavorite(model: ArticlesModel)
}