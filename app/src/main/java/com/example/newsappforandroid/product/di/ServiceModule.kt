package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.feature.news.news_sub.service.INewsService
import com.example.newsappforandroid.feature.news.news_sub.service.NewsService
import com.example.newsappforandroid.product.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun getRetrofitHomeServiceInstance(retrofit: Retrofit) : NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun getNewsServiceInstance(newsApi: NewsApi): INewsService {
        return NewsService(newsApi)
    }
}