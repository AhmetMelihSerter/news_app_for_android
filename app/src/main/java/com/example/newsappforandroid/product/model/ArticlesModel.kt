package com.example.newsappforandroid.product.model

import android.os.Parcelable
import com.example.newsappforandroid.product.utils.DateFormat
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
    val publishedAtParseDate : String get() {
        var result = publishedAt
        try {
            result = DateFormat.formatDate(publishedAt)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticlesModel

        if (source != other.source) return false
        if (author != other.author) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (url != other.url) return false
        if (urlToImage != other.urlToImage) return false
        if (publishedAt != other.publishedAt) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        return result
    }
}
