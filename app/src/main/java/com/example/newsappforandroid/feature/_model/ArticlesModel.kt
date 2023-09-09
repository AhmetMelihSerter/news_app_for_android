package com.example.newsappforandroid.feature._model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class ArticlesModel(
    val source: SourceModel,
    val sourceId: Int,
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
            val beforeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val afterFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            val parsedDate = beforeFormatter.parse(publishedAt)

            parsedDate?.let {
                result = afterFormatter.format(it)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
