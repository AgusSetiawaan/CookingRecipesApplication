package com.example.cookingrecipesapplication.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.core.domain.usecase.RecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase): ViewModel() {

    fun getRecipeDetail(recipes: Recipes) = recipesUseCase.getDetailRecipes(recipes).asLiveData()
}