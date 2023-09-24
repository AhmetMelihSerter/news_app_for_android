package com.example.newsappforandroid.product.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsappforandroid.core.utils.DateFormat
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.ARTICLES_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ARTICLES_TABLE)
data class ArticlesModel(
    @PrimaryKey(autoGenerate = true)
    var articlesId: Int,
    @Embedded(prefix = "source_")
    val source: SourceModel,
    val author: String?,
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

    override fun hashCode(): Int {
        return articlesId.hashCode()
    }
}
