package com.example.cookingrecipesapplication.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cookingrecipesapplication.MainActivity
import com.example.cookingrecipesapplication.adapter.DataItem
import com.example.cookingrecipesapplication.adapter.IngredientListAdapter
import com.example.cookingrecipesapplication.core.data.Resource
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.databinding.FragmentDetailLayoutBinding
import com.example.cookingrecipesapplication.databinding.RecipeDetailLayoutBinding
import com.example.cookingrecipesapplication.utils.afterMeasure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailScreenView: Fragment() {

    private lateinit var binding: FragmentDetailLayoutBinding
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipes = RecipeDetailScreenViewArgs.fromBundle(arguments as Bundle).recipes

        viewModel.getRecipeDetail(recipes).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    setData(it.data!!)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setData(recipes: Recipes){
        Log.d("recipe", recipes.toString())

        (activity as MainActivity).supportActionBar?.title = recipes.title

        Glide.with(requireContext())
            .load(recipes.detailImage)
            .centerCrop()
            .into(binding.recipesImage)

        // set List
        val adapter = IngredientListAdapter(requireContext())
        val items = listOf(DataItem.Header) + recipes.ingredients.map { DataItem.IngredientItem(it) } +
                listOf(DataItem.SummaryItem(recipes.summary))
        adapter.submitList(items)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.contentDetail.rvIngredients.layoutManager = layoutManager
        binding.contentDetail.rvIngredients.adapter = adapter

    }
}