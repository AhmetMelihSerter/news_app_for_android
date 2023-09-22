package com.example.newsappforandroid.product.init.database.dao

import androidx.room.*
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TABLE
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TITLE
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.SOURCES_TABLE
import com.example.newsappforandroid.product.init.database.entity.ArticlesEntity
import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity
import com.example.newsappforandroid.product.init.database.entity.SourceEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: ArticlesEntity)

    @Transaction
    @Query("SELECT * FROM $ARTICLES_TABLE")
    fun getAllFavorites(): List<FavoritesEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM $ARTICLES_TABLE WHERE $ARTICLES_TITLE = :title)")
    fun doFavoriteExist(title: String): Boolean?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateArticles(articles: ArticlesEntity)

    @Delete
    fun deleteArticles(articles: ArticlesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(articles: SourceEntity)

    @Query("Select * from $SOURCES_TABLE")
    fun getAllSource(): List<SourceEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSource(articles: SourceEntity)

    @Delete
    fun deleteSource(articles: SourceEntity)
}