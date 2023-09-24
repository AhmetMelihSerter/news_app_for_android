package com.example.newsappforandroid.product.init.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.init.database.converters.DateConverter
import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.product.model.SourceModel

@Database(entities = [ArticlesModel::class, SourceModel::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDao
}