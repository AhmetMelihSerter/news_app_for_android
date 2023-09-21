package com.example.newsappforandroid.product.di

import com.example.newsappforandroid.product.init.network.NewsApi
import com.example.newsappforandroid.product.constants.product.ProductConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 20L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 120L

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(ProductConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logInterceptor: HttpLoggingInterceptor): OkHttpClient {
        logInterceptor.setLevel(Level.NONE)
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, SECONDS)
            .readTimeout(READ_TIMEOUT, SECONDS)
            .writeTimeout(WRITE_TIMEOUT, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitServiceInstance(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}