package com.example.newsappforandroid.feature.news.news_sub.service

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import com.example.newsappforandroid.product.init.network.NewsApi
import javax.inject.Inject

class NewsService @Inject constructor(private val api: NewsApi) : INewsService {
    override suspend fun searchNews(
        model: NewsRequest,
    ) : NewsResponse? = api.searchNews(model.toMap())

}