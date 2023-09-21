package com.example.newsappforandroid.product.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsappforandroid.core.base.parser.BaseParser
import com.example.newsappforandroid.product.entity.ArticlesEntity
import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.product.entity.SourceEntity
import com.example.newsappforandroid.product.model.SourceModel
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class DbModelConverter(private val jsonParser: BaseParser) {

    @TypeConverter
    fun fromArticlesModel(json: String): ArticlesModel? {
        return jsonParser.fromJson(
            json,
            object: TypeToken<ArticlesModel>(){}.type
        )
    }

    @TypeConverter
    fun toArticlesEntity(model: ArticlesModel): String? {
        return jsonParser.toJson(model, object : TypeToken<ArticlesModel>(){}.type)
    }

    @TypeConverter
    fun fromSourceModel(json: String): SourceModel? {
        return jsonParser.fromJson(
            json,
            object: TypeToken<SourceModel>(){}.type
        )
    }
}