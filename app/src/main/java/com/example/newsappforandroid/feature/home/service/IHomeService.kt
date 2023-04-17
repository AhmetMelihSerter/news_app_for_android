package com.example.newsappforandroid.feature.home.service

import com.example.newsappforandroid.feature.home.model.HomeRequest
import com.example.newsappforandroid.feature.home.model.HomeResponse
import retrofit2.Response

interface IHomeService {

    suspend fun searchNews(model: HomeRequest) : Response<HomeResponse>
}