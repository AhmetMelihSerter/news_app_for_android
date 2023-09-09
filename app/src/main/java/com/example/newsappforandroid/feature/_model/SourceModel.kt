package com.example.newsappforandroid.feature._model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sources")
@Parcelize
data class SourceModel(
    @PrimaryKey(autoGenerate = true) var sourceId: Int,
    val id: String,
    val name: String
): Parcelable
