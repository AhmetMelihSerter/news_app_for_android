package com.example.newsappforandroid.feature.news.news_sub.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappforandroid.feature.news.news_sub.model.NewsRequest
import com.example.newsappforandroid.product.init.network.NewsApi
import com.example.newsappforandroid.product.model.ArticlesModel
import retrofit2.HttpException
import java.io.IOException

class NewsPagingDataSource(private val apiService: NewsApi, private val model: NewsRequest) :
    PagingSource<Int, ArticlesModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesModel> {
        val page = params.key ?: 1

        return try {
            model.page = page
            val response = apiService.searchNews(model.toMap())

            val items = response?.articles.orEmpty()

            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}