package com.example.newsappforandroid.feature.favorites.service

import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.product.init.storage.FavoritesDao
import javax.inject.Inject

class FavoritesService @Inject constructor(private val dao: FavoritesDao): IFavoritesService {

    override suspend fun gelAllFavorite() : List<ArticlesModel> {
        return dao.getAll()
    }

    override suspend fun addFavorite(model: ArticlesModel) {
        dao.insert(model)
    }

    override suspend fun deleteFavorite(model: ArticlesModel) {
        dao.delete(model)
    }
}