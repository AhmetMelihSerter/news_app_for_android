package com.example.newsappforandroid.product.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ArticleSourceForeignKeys
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.SOURCES_TABLE

@Entity(tableName = SOURCES_TABLE)
data class SourceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ArticleSourceForeignKeys.CHILD_KEY)
    var sourceId: Int,
    val id: String,
    val name: String
)