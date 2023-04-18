package com.example.newsappforandroid.feature.news.news_sub.model

import com.example.newsappforandroid.feature._model.ArticlesModel

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticlesModel>
)
