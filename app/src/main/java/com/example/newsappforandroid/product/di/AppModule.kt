package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.core.base.parser.BaseParser
import com.example.newsappforandroid.product.init.parser.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGsonParser(): BaseParser {
        return GsonParser(Gson())
    }
}