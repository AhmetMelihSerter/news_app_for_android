package com.example.newsappforandroid.feature.favorites.view

import com.example.newsappforandroid.product.adapter.ArticleListAdapter
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentFavoritesBinding
import com.example.newsappforandroid.feature.favorites.view_model.FavoritesViewModel
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {
    override val layoutId: Int = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: ArticleListAdapter

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        super.onViewModelReady(savedInstanceState)
        initializeAdapter()
        recycleAdapterDataListener()
    }

    private fun initializeAdapter() {
        adapter = ArticleListAdapter(::handlerAdapter)
        binding.recyclerViewNews.adapter = adapter
    }

    private fun handlerAdapter(model: ArticlesModel) {
        viewModel.adapterItemOnClick(model)
    }

    private fun recycleAdapterDataListener() {
        viewModel.favoritesList.observe(viewLifecycleOwner) {
            recycleAdapterAnimation(it)
            if (it.isNotEmpty()) adapter.submitList(it)
        }
    }

    private fun recycleAdapterAnimation(it: List<ArticlesModel>) {
        val anim = AnimationUtils.loadAnimation(
            binding.recyclerViewNews.context,
            if (it.isEmpty()) R.anim.anim_fade_in_down else R.anim.anim_fade_in_up
        )
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                if (it.isEmpty()) adapter.submitList(it)
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })
        binding.recyclerViewNews.startAnimation(anim)
    }
}