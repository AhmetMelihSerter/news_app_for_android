package com.example.newsappforandroid.feature.news.news_sub.model

import com.example.newsappforandroid.product.constants.product.ProductConstants.Companion.API_KEY

data class NewsRequest(
    var q: String,
    var page: Int = 1,
    var apiKey: String = API_KEY,
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "q" to q,
            "page" to page,
            "apiKey" to apiKey
        )
    }

}