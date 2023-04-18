package com.example.newsappforandroid.feature.news.news_sub.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsBinding
import com.example.newsappforandroid.feature.news.news_sub.view_model.NewsViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel.initialize(binding, hideKeyboard)
        view?.viewTreeObserver?.addOnWindowFocusChangeListener { hasFocus ->
            Log.w("NewsFragment", "HasFocus: ${hasFocus}")
        }
        return binding.root
    }
}