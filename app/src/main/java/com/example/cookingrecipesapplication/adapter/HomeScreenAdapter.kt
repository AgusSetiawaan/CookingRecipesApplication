package com.example.cookingrecipesapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.databinding.ItemRecipesBinding

class HomeScreenAdapter(private val onClick: (Recipes) -> Unit): ListAdapter<Recipes, HomeScreenAdapter.ItemRecipesViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecipesViewHolder {
        val binding = ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemRecipesViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ItemRecipesViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null){
            holder.bind(data, onClick)
        }
    }

    class ItemRecipesViewHolder(private val binding: ItemRecipesBinding,
                                private val  context: Context): RecyclerView.ViewHolder(binding.root){

        fun bind(recipes: Recipes, onClick: (Recipes) -> Unit){
            binding.foodTitle.text = recipes.title
            Glide.with(context).load(recipes.image).into(binding.foodImage)

            binding.root.setOnClickListener {
                onClick(recipes)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipes>() {
            override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}