package com.example.newsappforandroid.feature.news.news_sub.service

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import com.example.newsappforandroid.product.network.NewsApi
import retrofit2.Response
import javax.inject.Inject

class NewsService @Inject constructor(private val api : NewsApi) : INewsService {
    override suspend fun searchNews(model: NewsRequest): Response<NewsResponse> {
        return api.searchNews(model.toMap())
    }
}