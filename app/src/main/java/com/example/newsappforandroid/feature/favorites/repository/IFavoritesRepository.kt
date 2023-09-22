package com.example.newsappforandroid.feature.favorites.repository

import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity
import com.example.newsappforandroid.product.model.ArticlesModel

interface IFavoritesRepository {
    suspend fun gelAllFavorite() : List<FavoritesEntity>
}