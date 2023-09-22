package com.example.newsappforandroid.feature.news.news_detail.repository

import com.example.newsappforandroid.product.init.database.converters.DbModelConverter
import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.model.ArticlesModel
import javax.inject.Inject

class NewsDetailRepository @Inject constructor(
    private val converter: DbModelConverter,
    private val favoritesDao: FavoritesDao,
) : INewsDetailRepository {

    override suspend fun checkFavorites(title: String): Boolean {
        val isFavorite =  favoritesDao.doFavoriteExist(title)
        return isFavorite ?: false
    }

    override suspend fun addFavorite(model: ArticlesModel) {
        val sourceEntity = converter.sourceModelToSourceEntity(model.source)
        favoritesDao.insertSource(sourceEntity)
        val articlesEntity = converter.articlesModelToArticlesEntity(model)
        articlesEntity.sourceId = sourceEntity.sourceId
        favoritesDao.insertArticles(articlesEntity)
    }

    override suspend fun deleteFavorite(model: ArticlesModel) {
        val articlesEntity = converter.articlesModelToArticlesEntity(model)
        favoritesDao.deleteArticles(articlesEntity)
    }
}