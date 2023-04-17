package com.example.newsappforandroid.feature.home.model

import com.example.newsappforandroid.feature._model.ArticlesModel

data class HomeResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticlesModel>
)
