package com.example.newsappforandroid.feature.news.news_source.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.core.constants.Extensions.launchAndRepeatWithViewLifecycle
import com.example.newsappforandroid.databinding.FragmentNewsSourceBinding
import com.example.newsappforandroid.feature.news.news_source.view_model.NewsSourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourceFragment : BaseFragment<FragmentNewsSourceBinding, NewsSourceViewModel>() {

    override val layoutId: Int = R.layout.fragment_news_source

    override val viewModel: NewsSourceViewModel by viewModels()

    private val safeArgs: NewsSourceFragmentArgs by navArgs()

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        viewModel.setArgs(safeArgs.url)
        webViewDataListener()
        super.onViewModelReady(savedInstanceState)
        appBarBackButtonListener()
    }

    private fun webViewDataListener() {
        launchAndRepeatWithViewLifecycle {
            viewModel.loadUrl.collect {
                binding.webView.loadUrl(it)
            }
        }
    }
    private fun appBarBackButtonListener() {
        val toolbar = binding.appBarLayout.materialToolbar
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
    }
}