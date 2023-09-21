package com.example.newsappforandroid.feature.favorites.repository

import androidx.room.*
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TABLE
import com.example.newsappforandroid.product.entity.ArticlesEntity

@Dao
interface FavoritesDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: ArticlesEntity)

    @Query("Select * from $ARTICLES_TABLE")
    fun getAll(): List<ArticlesEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(articles: ArticlesEntity)

    @Delete
    fun delete(articles: ArticlesEntity)
}