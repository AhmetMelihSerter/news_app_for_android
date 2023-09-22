package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.feature.favorites.repository.FavoritesRepository
import com.example.newsappforandroid.feature.favorites.repository.IFavoritesRepository
import com.example.newsappforandroid.feature.news.news_detail.repository.INewsDetailRepository
import com.example.newsappforandroid.feature.news.news_detail.repository.NewsDetailRepository
import com.example.newsappforandroid.feature.news.news_sub.repository.INewsRepository
import com.example.newsappforandroid.feature.news.news_sub.repository.NewsRepository
import com.example.newsappforandroid.product.init.network.NewsApi
import com.example.newsappforandroid.product.init.database.converters.DbModelConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepositoryInstance(newsApi: NewsApi): INewsRepository {
        return NewsRepository(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsDetailRepositoryInstance(
        converter: DbModelConverter,
        favoritesDao: FavoritesDao,
    ): INewsDetailRepository {
        return NewsDetailRepository(converter, favoritesDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepositoryInstance(favoritesDao: FavoritesDao): IFavoritesRepository {
        return FavoritesRepository(favoritesDao)
    }
}