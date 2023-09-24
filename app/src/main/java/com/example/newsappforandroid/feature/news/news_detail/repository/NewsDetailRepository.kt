package com.example.newsappforandroid.feature.news.news_detail.repository

import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.model.ArticlesModel
import javax.inject.Inject

class NewsDetailRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
) : INewsDetailRepository {

    override suspend fun checkFavorites(title: String): Boolean {
        val isFavorite =  favoritesDao.doFavoriteExist(title)
        return isFavorite ?: false
    }

    override suspend fun addFavorite(model: ArticlesModel) {
        favoritesDao.insertArticles(model)
    }

    override suspend fun deleteFavorite(model: ArticlesModel) {
        favoritesDao.deleteArticles(model)
    }
}