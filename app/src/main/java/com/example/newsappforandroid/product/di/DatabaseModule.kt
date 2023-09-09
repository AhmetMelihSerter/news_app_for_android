package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.product.init.storage.FavoritesDao
import com.example.newsappforandroid.product.init.storage.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): FavoritesDao {
        return newsDatabase.newsDao()
    }
}