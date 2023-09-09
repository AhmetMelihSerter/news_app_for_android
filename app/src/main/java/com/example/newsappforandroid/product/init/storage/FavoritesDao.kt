package com.example.newsappforandroid.product.init.storage

import androidx.room.*
import com.example.newsappforandroid.feature._model.ArticlesEntity

@Dao
interface FavoritesDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: ArticlesEntity)

    @Query("Select * from articles")
    fun getAll(): List<ArticlesEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(articles: ArticlesEntity)

    @Delete
    fun delete(articles: ArticlesEntity)
}