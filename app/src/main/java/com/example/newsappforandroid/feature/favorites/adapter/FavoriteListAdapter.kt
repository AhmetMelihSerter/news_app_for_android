package com.example.newsappforandroid.feature.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappforandroid.databinding.FavoritesCardViewBinding
import com.example.newsappforandroid.product.init.database.entity.FavoritesEntity

class FavoriteListAdapter(
    private val onItemClickListener: (item: FavoritesEntity) -> Unit,
) : ListAdapter<FavoritesEntity, FavoriteListAdapter.ModelViewHolder>(FavoritesEntityComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = FavoritesCardViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    object FavoritesEntityComparator : DiffUtil.ItemCallback<FavoritesEntity>() {
        override fun areItemsTheSame(oldItem: FavoritesEntity, newItem: FavoritesEntity): Boolean {
            return oldItem.articlesEntity.id == newItem.articlesEntity.id
        }

        override fun areContentsTheSame(oldItem: FavoritesEntity, newItem: FavoritesEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class ModelViewHolder(private val binding: FavoritesCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: FavoritesEntity) {
            with(binding) {
                item = value
                binding.cardView.setOnClickListener {
                    onItemClickListener.invoke(value)
                }
            }
        }
    }
}