package com.example.cookingrecipesapplication.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.cookingrecipesapplication.core.data.Resource
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.core.domain.usecase.RecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase): ViewModel() {

    fun getRecipesByType(type: String): LiveData<Resource<List<Recipes>>> =
        recipesUseCase.getRecipesByType(type).asLiveData()

}