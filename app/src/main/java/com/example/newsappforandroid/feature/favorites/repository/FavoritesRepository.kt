package com.example.newsappforandroid.feature.favorites.repository

import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.product.utils.DbModelConverter
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val converter: DbModelConverter,
    private val dao: FavoritesDao
) : IFavoritesRepository {

    override suspend fun gelAllFavorite(): List<ArticlesModel> {
        return emptyList()
        /*dao.getAll().map {
            converter.fromArticlesModel(it)
        }*/
    }

    override suspend fun addFavorite(model: ArticlesModel) {
        //dao.insert(converter.toArticlesEntity(model))
    }

    override suspend fun deleteFavorite(model: ArticlesModel) {
        //dao.delete(converter.toArticlesEntity(model))
    }
}