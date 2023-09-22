package com.example.newsappforandroid.product.init.database.entity

import androidx.room.*
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ArticleSourceForeignKeys
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TABLE

@Entity(
    tableName = ARTICLES_TABLE,
    indices = [
        Index(
            value = [ArticleSourceForeignKeys.PARENT_KEY],
            unique = true
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = SourceEntity::class,
            parentColumns = arrayOf(ArticleSourceForeignKeys.PARENT_KEY),
            childColumns = arrayOf(ArticleSourceForeignKeys.CHILD_KEY),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = ArticleSourceForeignKeys.PARENT_KEY)
    var sourceId: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)