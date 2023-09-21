package com.example.newsappforandroid.feature.news.news_sub.repository

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse

interface INewsRepository {

    suspend fun searchNews(
        model: NewsRequest,
    ): NewsResponse?
}