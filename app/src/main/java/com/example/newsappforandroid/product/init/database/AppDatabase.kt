package com.example.newsappforandroid.product.init.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappforandroid.product.init.database.entity.ArticlesEntity
import com.example.newsappforandroid.product.init.database.entity.SourceEntity
import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.init.database.converters.DbModelConverter

@Database(entities = [ArticlesEntity::class, SourceEntity::class], version = 1, exportSchema = false)
@TypeConverters(DbModelConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDao
}