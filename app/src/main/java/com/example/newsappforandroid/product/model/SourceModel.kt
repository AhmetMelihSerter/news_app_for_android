package com.example.newsappforandroid.product.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.SOURCES_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = SOURCES_TABLE)
data class SourceModel(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @Ignore
    val id: String,
    val name: String,
): Parcelable {
    constructor(_id: Int, name: String) : this(_id, "", name)
}
