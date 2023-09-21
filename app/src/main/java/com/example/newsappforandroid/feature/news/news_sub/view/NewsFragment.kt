package com.example.newsappforandroid.feature.news.news_sub.view

import ArticleListAdapter
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature.news.news_sub.view_model.NewsViewModel
import com.example.newsappforandroid.product.model.ArticlesModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override val layoutId: Int = R.layout.fragment_news

    override val viewModel: NewsViewModel by viewModels()

    private lateinit var adapter: ArticleListAdapter

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        initializeAdapter()
        super.onViewModelReady(savedInstanceState)
        searchNewsListener()
        searchNewsOnClearListener()
        recycleAdapterDataListener()
        recycleAdapterEndScrollListener()
    }

    private fun initializeAdapter() {
        adapter = ArticleListAdapter(::handlerAdapter)
        binding.recyclerViewNews.adapter = adapter
    }

    private fun handlerAdapter(model: ArticlesModel) {
        viewModel.adapterItemOnClick(model)
    }

    private fun searchNewsListener() {
        binding.searchNewsTextInputEditText.doAfterTextChanged {
            it?.let {
                viewModel.setSearchNewsText(it.toString())
            }
        }
    }

    private fun searchNewsOnClearListener() {
        binding.searchNewsInputLayout.setEndIconOnClickListener {
            binding.searchNewsTextInputEditText.text?.clear()
            binding.searchNewsInputLayout.clearFocus()
            viewModel.searchNewsOnClear()
        }
    }

    private fun recycleAdapterDataListener() {
        viewModel.articleList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun recycleAdapterEndScrollListener() {
        binding.recyclerViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Logger.i("Last Recycle")
                }
            }
        })
    }
}