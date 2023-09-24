package com.example.newsappforandroid.feature.favorites.repository

import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.model.ArticlesModel
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
) : IFavoritesRepository {

    override suspend fun gelAllFavorite(): List<ArticlesModel> {
        return favoritesDao.getAllFavorites()
    }
}