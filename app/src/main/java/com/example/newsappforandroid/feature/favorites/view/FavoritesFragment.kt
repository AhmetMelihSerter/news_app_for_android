package com.example.newsappforandroid.feature.favorites.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.newsappforandroid.R
import com.example.newsappforandroid.core.base.fragment.BaseFragment
import com.example.newsappforandroid.feature.favorites.view_model.FavoritesViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {
    override val layoutId: Int = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel by viewModels()
}