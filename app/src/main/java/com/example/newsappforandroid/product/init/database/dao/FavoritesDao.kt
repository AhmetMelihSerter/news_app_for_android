package com.example.newsappforandroid.product.init.database.dao

import androidx.room.*
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TABLE
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TITLE
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.SOURCES_TABLE
import com.example.newsappforandroid.product.model.ArticlesModel
import com.example.newsappforandroid.product.model.SourceModel

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: ArticlesModel)

    @Query("SELECT * FROM $ARTICLES_TABLE")
    fun getAllFavorites(): List<ArticlesModel>

    @Query("SELECT EXISTS (SELECT 1 FROM $ARTICLES_TABLE WHERE $ARTICLES_TITLE = :title)")
    fun doFavoriteExist(title: String): Boolean?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateArticles(articles: ArticlesModel)

    @Delete
    fun deleteArticles(articles: ArticlesModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(articles: SourceModel)

    @Query("Select * from $SOURCES_TABLE")
    fun getAllSource(): List<SourceModel>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSource(articles: SourceModel)

    @Delete
    fun deleteSource(articles: SourceModel)
}