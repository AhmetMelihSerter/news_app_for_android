package com.example.newsappforandroid.feature.news.news_detail.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature.news.news_detail.view_model.NewsDetailViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {

    override val layoutId: Int = R.layout.fragment_news_detail

    override val viewModel: NewsDetailViewModel by viewModels()

    private val safeArgs: NewsDetailFragmentArgs by navArgs()

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        viewModel.setArgs(safeArgs.article)
        super.onViewModelReady(savedInstanceState)
        shareDataListener()
        favoriteIconListener()
        appBarAddMenu()
    }

    private fun appBarAddMenu() {
        val toolbar = binding.appBarLayout.materialToolbar
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.share_data -> {
                    viewModel.shareUrl()
                    true
                }
                R.id.add_favorites -> {
                    viewModel.addFavorite()
                    true
                }
                else -> false
            }
        }
    }

    private fun favoriteIconListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isFavoriteChannel.collect {
                    handleFavoriteIcon(it)
                }
            }
        }
    }

    private fun handleFavoriteIcon(isFavorite: Boolean) {
        val menuItem = binding.appBarLayout.materialToolbar.menu.findItem(R.id.add_favorites)
        if (isFavorite) {
            menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorites)
            return
        }

        menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorites_border)
    }

    private fun shareDataListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.shareDataChannel.collect { data ->
                    handleShareData(data)
                }
            }
        }
    }

    private fun handleShareData(data: String) {
        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, data)
                type = "text/plain"
            }

            startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Logger.e("Error: $e")
        }
    }

}