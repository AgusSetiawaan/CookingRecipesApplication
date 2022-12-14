package com.example.cookingrecipesapplication.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.example.cookingrecipesapplication.core.data.source.local.entity.IngredientEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesIngredientsCrossRef
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes WHERE type = :type ORDER BY id")
    fun getRecipesByType(type: String): Flow<List<RecipesEntity>>

    @Query("SELECT * FROM recipes where isFavorite = 1")
    fun getFavoriteRecipes(): Flow<List<RecipesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipesEntity>)

    @Update
    suspend fun updateRecipeValue(recipe: RecipesEntity)

    @Query("DELETE FROM recipes WHERE type =:type")
    suspend fun deleteAllRecipes(type: String)

    @Transaction
    @Query("SELECT * FROM recipes where id = :id")
    fun getRecipesById(id: Int): Flow<RecipesWithIngredients>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipesIngredientCrossRef(recipesIngredientCrossRef: List<RecipesIngredientsCrossRef>)


}