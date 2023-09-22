package com.example.newsappforandroid.feature.news.news_detail.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentNewsDetailBinding
import com.example.newsappforandroid.feature.news.news_detail.view_model.NewsDetailViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    viewModel.addOrRemoveFavorite()
                    true
                }
                else -> false
            }
        }
    }

    private fun favoriteIconListener() {
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            val menuItem = binding.appBarLayout.materialToolbar.menu.findItem(R.id.add_favorites)
            val favoriteIconSelected =
                if (it) R.drawable.ic_favorites_24 else R.drawable.ic_favorites_border_24

            menuItem.icon = ContextCompat.getDrawable(
                requireContext(),
                favoriteIconSelected,
            )
        }
    }

    private fun shareDataListener() {
        viewModel.shareData.observe(viewLifecycleOwner) {
            try {
                if (it.isNotEmpty()) {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, data)
                        type = "text/plain"
                    }

                    startActivity(sendIntent)
                }
            } catch (e: ActivityNotFoundException) {
                Logger.e("Error: $e")
            }
        }
    }
}