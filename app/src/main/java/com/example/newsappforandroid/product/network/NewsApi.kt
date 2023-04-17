package com.example.newsappforandroid.product.network

import com.example.newsappforandroid.feature.home.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("search")
    suspend fun searchNews(
        @QueryMap
        queryParams: MutableMap<String, Any>
    ): Response<HomeResponse>
}