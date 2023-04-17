package com.example.newsappforandroid.feature.home.model

import com.example.newsappforandroid.product.constants.ProductConstants.Companion.API_KEY

data class HomeRequest(
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