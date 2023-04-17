package com.example.newsappforandroid.feature.home.service

import com.example.newsappforandroid.feature.home.model.HomeRequest
import com.example.newsappforandroid.feature.home.model.HomeResponse
import com.example.newsappforandroid.product.network.NewsApi
import retrofit2.Response
import javax.inject.Inject

class HomeService @Inject constructor(private val api : NewsApi) : IHomeService {
    override suspend fun searchNews(model: HomeRequest): Response<HomeResponse> {
        return api.searchNews(model.toMap())
    }

}