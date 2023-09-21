package com.example.newsappforandroid.product.init.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappforandroid.product.entity.ArticlesEntity
import com.example.newsappforandroid.product.entity.SourceEntity
import com.example.newsappforandroid.feature.favorites.repository.FavoritesDao
import com.example.newsappforandroid.product.utils.DbModelConverter

@Database(entities = [ArticlesEntity::class, SourceEntity::class], version = 1, exportSchema = false)
@TypeConverters(DbModelConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao() : FavoritesDao
}