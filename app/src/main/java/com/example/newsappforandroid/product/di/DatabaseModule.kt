package com.example.newsappforandroid.product.di

import android.content.Context
import androidx.room.Room
import com.example.newsappforandroid.product.init.database.dao.FavoritesDao
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.NEWS_DATABASE
import com.example.newsappforandroid.product.init.database.AppDatabase
import com.example.newsappforandroid.product.init.database.converters.DateConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, NEWS_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideArticlesDao(newsDatabase: AppDatabase): FavoritesDao {
        return newsDatabase.favoritesDao()
    }

    @Provides
    @Singleton
    fun provideDateConverter(): DateConverter {
        return DateConverter()
    }
}