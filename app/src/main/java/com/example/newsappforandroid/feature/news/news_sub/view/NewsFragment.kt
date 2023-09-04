package com.example.newsappforandroid.feature.news.news_sub.view

import ArticleListAdapter
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature.news.news_sub.view_model.NewsViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override val layoutId: Int = R.layout.fragment_news

    override val viewModel: NewsViewModel by viewModels()

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        viewModel.articleListAdapter = ArticleListAdapter(requireContext())
        super.onViewModelReady(savedInstanceState)
    }
}