package com.example.newsappforandroid.feature.news.news_detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.R
import com.example.newsappforandroid.feature.news.news_detail.view_model.NewsDetailViewModel

class NewsDetailFragment : Fragment() {

    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

}