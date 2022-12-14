package com.example.cookingrecipesapplication.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.cookingrecipesapplication.core.data.Resource
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import kotlinx.coroutines.flow.Flow

interface IRecipesRepository {

    fun getRecipesByType(type: String): Flow<Resource<List<Recipes>>>

    fun getDetailRecipes(recipes: Recipes): Flow<Resource<Recipes>>
}