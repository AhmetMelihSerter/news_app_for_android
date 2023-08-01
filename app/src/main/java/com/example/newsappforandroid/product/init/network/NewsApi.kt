package com.example.newsappforandroid.product.init.network

import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("everything")
    suspend fun searchNews(
        @QueryMap
        queryParams: MutableMap<String, Any>,
    ): NewsResponse?
}