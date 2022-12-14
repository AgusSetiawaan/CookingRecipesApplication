package com.example.cookingrecipesapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingrecipesapplication.R
import com.example.cookingrecipesapplication.core.domain.model.Ingredient
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.databinding.ItemIngredientsBinding
import com.example.cookingrecipesapplication.databinding.ItemRecipesBinding
import com.example.cookingrecipesapplication.databinding.ItemTitleListBinding

class IngredientListAdapter(private val context: Context): ListAdapter<DataItem, RecyclerView.ViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ITEM_LIST_VIEW_TYPE){
            val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            IngredientViewHolder(binding, parent.context)
        } else{
            val binding = ItemTitleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            TitleViewHolder(binding)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItem(position) is DataItem.Header) {
            (holder as TitleViewHolder).bind(context.getString(R.string.ingredients) +
                    " (${currentList.filterIsInstance<DataItem.IngredientItem>().size})", "")
        }
        else if(getItem(position) is DataItem.IngredientItem){
            val item = getItem(position) as DataItem.IngredientItem
            item.let {
                (holder as IngredientViewHolder).bind(it)
            }
        }
        else{
            (holder as TitleViewHolder).bind("Summary", HtmlCompat.fromHtml((getItem(position) as DataItem.SummaryItem).summary, HtmlCompat.FROM_HTML_MODE_LEGACY).toString())
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Header -> TITLE_VIEW_TYPE
            is DataItem.IngredientItem -> ITEM_LIST_VIEW_TYPE
            else -> SUMMARY_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int = currentList.size

    inner class IngredientViewHolder(private val binding: ItemIngredientsBinding,
                                     private val context: Context): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: DataItem.IngredientItem){
            binding.ingredientTitle.text = item.ingredient.name
            binding.ingredientMeasures.text = "${item.ingredient.amount} ${item.ingredient.unit}"
            Glide.with(context)
                .load(item.ingredient.image)
                .into(binding.foodImage)
        }
    }

    inner class TitleViewHolder(private val binding: ItemTitleListBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(title: String, description: String){
            binding.title.text = title
            binding.description.visibility = if(description.isEmpty()) View.GONE else View.VISIBLE
            binding.description.text = description
        }
    }

    companion object {

        private const val TITLE_VIEW_TYPE = 0
        private const val ITEM_LIST_VIEW_TYPE = 1
        private const val SUMMARY_VIEW_TYPE = 2

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

sealed class DataItem{
    abstract val id: Int

    data class IngredientItem(val ingredient: Ingredient): DataItem(){
        override val id = ingredient.id
    }

    object Header: DataItem(){
        override val id: Int
            get() = Int.MIN_VALUE
    }

    data class SummaryItem(val summary: String): DataItem(){
        override val id: Int
            get() = Int.MAX_VALUE

    }
}

