package com.example.newsappforandroid.feature.favorites.view

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.databinding.FragmentFavoritesBinding
import com.example.newsappforandroid.feature.favorites.adapter.FavoriteListAdapter
import com.example.newsappforandroid.feature.favorites.view_model.FavoritesViewModel
import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity
import com.example.newsappforandroid.product.model.ArticlesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {
    override val layoutId: Int = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: FavoriteListAdapter

    override fun onViewModelReady(savedInstanceState: Bundle?) {
        super.onViewModelReady(savedInstanceState)
        initializeAdapter()
        recycleAdapterDataListener()
    }

    private fun initializeAdapter() {
        adapter = FavoriteListAdapter(::handlerAdapter)
        binding.recyclerViewNews.adapter = adapter
    }

    private fun handlerAdapter(model: FavoritesEntity) {
        viewModel.adapterItemOnClick(model)
    }

    private fun recycleAdapterDataListener() {
        viewModel.favoritesList.observe(viewLifecycleOwner) {
            recycleAdapterAnimation(it)
            if (it.isNotEmpty()) adapter.submitList(it)
        }
    }

    private fun recycleAdapterAnimation(it: List<FavoritesEntity>) {
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