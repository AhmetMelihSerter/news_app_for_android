package com.example.newsappforandroid.feature.favorites.repository

import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
) : IFavoritesRepository {

    override suspend fun gelAllFavorite(): List<FavoritesEntity> {
        return favoritesDao.getAllFavorites()
    }
}