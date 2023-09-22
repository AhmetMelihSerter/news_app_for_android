package com.example.newsappforandroid.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {

    fun formatDate(
        date: String,
        beforePattern: String = "yyyy-MM-dd'T'HH:mm:ss",
        afterPattern: String = "dd.MM.yyyy"
    ): String {
        var result: String = date
        val beforeFormatter = SimpleDateFormat(beforePattern, Locale.getDefault())
        val afterFormatter = SimpleDateFormat(afterPattern, Locale.getDefault())

        val parsedDate = beforeFormatter.parse(date)

        parsedDate?.let {
            result = afterFormatter.format(it)
        }
        return result
    }
}