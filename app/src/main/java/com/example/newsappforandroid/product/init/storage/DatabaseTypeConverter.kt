package com.example.newsappforandroid.product.init.storage

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsappforandroid.feature._model.ArticlesEntity
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.google.gson.Gson

@ProvidedTypeConverter
class DatabaseTypeConverter {

    /*@TypeConverter
    fun ArticlesModelArticlesEntity(model: ArticlesModel): ArticlesEntity {
        val modelJson = Gson().toJson(model)
        return Gson().fromJson(modelJson)
    }*/
}