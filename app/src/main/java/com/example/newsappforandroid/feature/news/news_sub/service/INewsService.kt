package com.example.newsappforandroid.feature.news.news_sub.service

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse

interface INewsService {

    suspend fun searchNews(
        model: NewsRequest,
    ): NewsResponse?
}