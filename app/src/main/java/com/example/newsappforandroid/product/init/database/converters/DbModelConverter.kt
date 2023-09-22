package com.example.newsappforandroid.product.init.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsappforandroid.core.base.parser.BaseParser
import com.example.newsappforandroid.product.init.database.entity.ArticlesEntity
import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.product.init.database.entity.SourceEntity
import com.example.newsappforandroid.product.model.SourceModel
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class DbModelConverter(private val jsonParser: BaseParser) {

    @TypeConverter
    fun jsonToArticlesModel(json: String): ArticlesModel {
        return jsonParser.fromJson(
            json,
            object : TypeToken<ArticlesModel>() {}.type
        )
    }

    @TypeConverter
    fun articlesModelToJson(model: ArticlesModel): String {
        return jsonParser.toJson(model, object : TypeToken<ArticlesModel>() {}.type)
    }

    @TypeConverter
    fun articlesModelToArticlesEntity(model: ArticlesModel): ArticlesEntity {
        val json = jsonParser.toJson(model, object : TypeToken<ArticlesModel>() {}.type)
        return jsonToArticlesEntity(json)
    }

    @TypeConverter
    fun articlesEntityToArticlesModel(model: ArticlesEntity): ArticlesModel {
        val json = jsonParser.toJson(model, object : TypeToken<ArticlesEntity>() {}.type)
        return jsonToArticlesModel(json)
    }

    @TypeConverter
    fun jsonToArticlesEntity(json: String): ArticlesEntity {
        return jsonParser.fromJson(
            json,
            object : TypeToken<ArticlesEntity>() {}.type
        )
    }

    @TypeConverter
    fun articlesEntityToJson(model: ArticlesEntity): String {
        return jsonParser.toJson(model, object : TypeToken<ArticlesEntity>() {}.type)
    }

    @TypeConverter
    fun jsonToSourceModel(json: String): SourceModel {
        return jsonParser.fromJson(
            json,
            object : TypeToken<SourceModel>() {}.type
        )
    }

    @TypeConverter
    fun sourceModelToJson(model: SourceModel): String {
        return jsonParser.toJson(model, object : TypeToken<SourceModel>() {}.type)
    }

    @TypeConverter
    fun sourceModelToSourceEntity(model: SourceModel): SourceEntity {
        val json = jsonParser.toJson(model, object : TypeToken<SourceModel>() {}.type)
        return jsonToSourceEntity(json)
    }

    @TypeConverter
    fun jsonToSourceEntity(json: String): SourceEntity {
        return jsonParser.fromJson(
            json,
            object : TypeToken<SourceEntity>() {}.type
        )
    }

    @TypeConverter
    fun sourceEntityToJson(model: SourceEntity): String {
        return jsonParser.toJson(model, object : TypeToken<SourceEntity>() {}.type)
    }
}