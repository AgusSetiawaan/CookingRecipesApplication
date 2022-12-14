package com.example.cookingrecipesapplication.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.cookingrecipesapplication.core.data.Resource
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.core.domain.repository.IRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesInteractor @Inject constructor(private val recipesRepository: IRecipesRepository):
    RecipesUseCase {

    override fun getRecipesByType(type: String): Flow<Resource<List<Recipes>>> =
        recipesRepository.getRecipesByType(type)

    override fun getDetailRecipes(recipes: Recipes): Flow<Resource<Recipes>> =
        recipesRepository.getDetailRecipes(recipes)
}