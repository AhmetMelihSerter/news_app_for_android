package com.example.newsappforandroid.feature.news.news_detail.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature.news.news_detail.view_model.NewsDetailViewModel
import com.orhanobut.logger.Logger

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {

    override val layoutId: Int = R.layout.fragment_news_detail

    override val viewModel: NewsDetailViewModel by viewModels()

    private val safeArgs: NewsDetailFragmentArgs by navArgs()

    override fun onViewModelPre(savedInstanceState: Bundle?) {
        viewModel.setArgs(safeArgs.article)
    }

}