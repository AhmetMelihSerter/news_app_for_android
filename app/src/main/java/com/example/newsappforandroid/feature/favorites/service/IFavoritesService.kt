package com.example.newsappforandroid.feature.favorites.service

import com.example.newsappforandroid.feature._model.ArticlesModel

interface IFavoritesService {
    suspend fun gelAllFavorite() : List<ArticlesModel>

    suspend fun addFavorite(model: ArticlesModel)

    suspend fun deleteFavorite(model: ArticlesModel)
}