package com.example.newsappforandroid.retrofit_unit_test

import android.util.Log
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.product.constants.product.ProductConstants.Companion.BASE_URL_TEST
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {

    private val instance: Retrofit = MockRetrofitClient().retrofit

    @Test
    fun testRetrofitInstance() {
        assert(instance.baseUrl().toUrl().toString() == BASE_URL_TEST)
    }

    @Test
    suspend fun testNewsRequest() {
        val service = MockNewsService(instance)

        val status = service.searchNews(
            NewsRequest(
                q = "Beşiktaş",
                page = 1,
            )
        )?.status ?: "fail"

        Log.e("Error", "status ${status}")

        assert(status == "ok")
    }
}