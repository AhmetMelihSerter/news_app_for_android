package com.example.newsappforandroid.feature._model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesModel(
    val source: SourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
): Parcelable
{
    override fun hashCode(): Int {
        return title.hashCode()
    }
}
