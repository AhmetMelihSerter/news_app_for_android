package com.example.newsappforandroid.product.init.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappforandroid.feature._model.ArticlesModel

@Database(entities = [ArticlesModel::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao() : FavoritesDao
}