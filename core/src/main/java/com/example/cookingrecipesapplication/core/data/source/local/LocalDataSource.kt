package com.example.cookingrecipesapplication.core.data.source.local

import androidx.paging.PagingSource
import com.example.cookingrecipesapplication.core.data.source.local.entity.*
import com.example.cookingrecipesapplication.core.data.source.local.room.RecipesDao
import com.example.cookingrecipesapplication.core.data.source.local.room.RecipesDatabase
import com.example.cookingrecipesapplication.core.data.source.local.room.RemoteKeysDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao,
                                          private val remoteKeysDao: RemoteKeysDao,
                                          private val recipesDatabase: RecipesDatabase
) {

    fun getRecipesByType(type: String): Flow<List<RecipesEntity>> = recipesDao.getRecipesByType(type)

    fun getFavoriteRecipes(): Flow<List<RecipesEntity>> = recipesDao.getFavoriteRecipes()

    suspend fun insertRecipes(recipes: List<RecipesEntity>) = recipesDao.insertRecipes(recipes)

    suspend fun updateRecipeValue(recipe: RecipesEntity) = recipesDao.updateRecipeValue(recipe)

    suspend fun deleteAllRecipes(type: String) = recipesDao.deleteAllRecipes(type)

    fun getRecipeById(id: Int): Flow<RecipesWithIngredients> = recipesDao.getRecipesById(id)

    suspend fun insertIngredients(ingredients: List<IngredientEntity>) = recipesDao.insertIngredients(ingredients)

    suspend fun insertRecipesIngredientCrossRef(recipesIngredientCrossRef: List<RecipesIngredientsCrossRef>) =
        recipesDao.insertRecipesIngredientCrossRef(recipesIngredientCrossRef)

    suspend fun insertAllRemoteKeys(remoteKeys: List<RemoteKeys>) = remoteKeysDao.insertAll(remoteKeys)

    suspend fun getRemoteKeysById(id: String) = remoteKeysDao.getRemoteKeysById(id)

    suspend fun deleteRemoteKeys() = remoteKeysDao.deleteRemoteKeys()


    fun getDatabase(): RecipesDatabase = recipesDatabase

}