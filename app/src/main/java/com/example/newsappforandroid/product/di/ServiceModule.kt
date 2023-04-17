package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.feature.home.service.IHomeService
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
    fun getRetrofitHomeServiceInstance(retrofit: Retrofit) : IHomeService {
        return retrofit.create(IHomeService::class.java)
    }
}