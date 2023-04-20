package com.example.newsappforandroid.feature.news.news_sub.service

import androidx.lifecycle.MutableLiveData
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse

interface INewsService {

    suspend fun searchNews(
        model: NewsRequest,
        liveData: MutableLiveData<List<ArticlesModel>>
    )
}