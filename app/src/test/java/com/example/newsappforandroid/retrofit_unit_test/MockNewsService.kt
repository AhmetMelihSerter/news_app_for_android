package com.example.newsappforandroid.retrofit_unit_test

import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import com.example.newsappforandroid.feature.news.news_sub.repository.INewsRepository
import com.example.newsappforandroid.product.init.network.NewsApi
import retrofit2.Retrofit

class MockNewsService constructor(private val retrofit: Retrofit) : INewsRepository {
    private val endpoint by lazy {
        retrofit.create(NewsApi::class.java)
    }

    override suspend fun searchNews(model: NewsRequest): NewsResponse? {
        return endpoint.searchNews(model.toMap())
    }
}