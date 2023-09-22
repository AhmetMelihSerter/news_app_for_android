package com.example.newsappforandroid.feature.news.news_detail.repository

import com.example.newsappforandroid.product.model.ArticlesModel

interface INewsDetailRepository {
    suspend fun addFavorite(model: ArticlesModel)

    suspend fun deleteFavorite(model: ArticlesModel)
    suspend fun checkFavorites(title: String): Boolean
}