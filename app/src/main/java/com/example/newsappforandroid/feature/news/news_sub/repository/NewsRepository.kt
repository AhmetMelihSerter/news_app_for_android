package com.example.newsappforandroid.feature.news.news_sub.repository

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import com.example.newsappforandroid.product.init.network.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: NewsApi) : INewsRepository {
    override suspend fun searchNews(
        model: NewsRequest,
    ): NewsResponse? = apiService.searchNews(model.toMap())
}