package com.example.newsappforandroid.retrofit_unit_test

import com.example.newsappforandroid.product.constants.product.ProductConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MockRetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(okHttpClient).baseUrl(ProductConstants.BASE_URL_TEST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}