package com.example.newsappforandroid.product.init.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsappforandroid.core.base.parser.BaseParser
import java.util.*

@ProvidedTypeConverter
class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}