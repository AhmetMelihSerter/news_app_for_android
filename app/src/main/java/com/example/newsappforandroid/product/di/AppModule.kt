package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.BuildConfig
import com.example.newsappforandroid.product.constants.product.ProductConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun logInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            interceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return interceptor
    }
}