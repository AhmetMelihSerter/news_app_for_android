package com.example.newsappforandroid.feature.news.news_sub.service

import androidx.lifecycle.MutableLiveData
import com.example.newsappforandroid.feature._model.ArticlesModel
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.feature.news.news_sub.model.NewsResponse
import com.example.newsappforandroid.product.network.NewsApi
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsService @Inject constructor(private val api: NewsApi) : INewsService {
    override suspend fun searchNews(
        model: NewsRequest,
        liveData: MutableLiveData<List<ArticlesModel>>
    ) {
        val call = api.searchNews(model.toMap())

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body()!!.articles)
                    Logger.i("Successfully")
                } else {
                    Logger.i("Not Successfully")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Logger.e("Error")
            }
        })

        /*
        var value: NewsResponse? = null

        try {
            val response = call.execute()

            value = response.body()

            if (response.body() == null) {
                Logger.wtf("Response Body Null")
            }
        } catch (error: Exception) {
            Logger.e("Error: $error")
        }

        return value*/
    }
}