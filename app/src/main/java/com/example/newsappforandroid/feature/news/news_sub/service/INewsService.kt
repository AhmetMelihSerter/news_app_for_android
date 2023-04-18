package com.example.newsappforandroid.feature.news.news_sub.service

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import retrofit2.Response

interface INewsService {

    suspend fun searchNews(model: NewsRequest) : Response<NewsResponse>
}