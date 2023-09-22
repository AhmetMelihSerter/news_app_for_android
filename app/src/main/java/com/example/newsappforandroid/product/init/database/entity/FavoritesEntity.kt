package com.example.newsappforandroid.product.init.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ArticleSourceForeignKeys

data class FavoritesEntity(
    @Relation(
        parentColumn = ArticleSourceForeignKeys.PARENT_KEY,
        entityColumn = ArticleSourceForeignKeys.CHILD_KEY,
    )
    val sourceEntity: SourceEntity,
    @Embedded
    val articlesEntity: ArticlesEntity,
)