package com.example.newsappforandroid.feature.news.news_source.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsSourceBinding
import com.example.newsappforandroid.feature.news.news_source.view_model.NewsSourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourceFragment : BaseFragment() {

    private val viewModel: NewsSourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewsSourceBinding.inflate(inflater, container, false)
        viewModel.initialize(binding, hideKeyboard)
        return binding.root
    }
}