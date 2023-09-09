package com.example.newsappforandroid.feature._model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "articles",
    foreignKeys = [ForeignKey(
        entity = SourceModel::class,
        parentColumns = arrayOf("sourceId"),
        childColumns = arrayOf("sourceId"),
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val sourceId: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
): Parcelable