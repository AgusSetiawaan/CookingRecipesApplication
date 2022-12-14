package com.example.cookingrecipesapplication.core.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.cookingrecipesapplication.core.data.source.local.LocalDataSource
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesIngredientsCrossRef
import com.example.cookingrecipesapplication.core.data.source.remote.RemoteDataSource
import com.example.cookingrecipesapplication.core.data.source.remote.network.ApiResponse
import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesDetailResponse
import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesResponse
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.core.domain.repository.IRecipesRepository
import com.example.cookingrecipesapplication.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IRecipesRepository {

    override fun getRecipesByType(type: String): Flow<Resource<List<Recipes>>> =
        object: NetworkBoundResource<List<Recipes>, List<RecipesResponse>>(){
            override fun loadFromDB(): Flow<List<Recipes>> {
                return localDataSource.getRecipesByType(type).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Recipes>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<RecipesResponse>>> {
                return remoteDataSource.searchRecipesByType(type, 0, 20)
            }

            override suspend fun saveCallResult(data: List<RecipesResponse>) {
                localDataSource.insertRecipes(DataMapper.mapResponsesToEntities(data, type))
            }

        }.asFlow()

    override fun getDetailRecipes(recipes: Recipes): Flow<Resource<Recipes>>  =
        object: NetworkBoundResource<Recipes, RecipesDetailResponse>(){
            override fun loadFromDB(): Flow<Recipes> {
                return localDataSource.getRecipeById(recipes.id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Recipes?): Boolean {
                return data == null || data.ingredients.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<RecipesDetailResponse>> {
                return remoteDataSource.getDetailRecipes(recipes.id)
            }

            override suspend fun saveCallResult(data: RecipesDetailResponse) {
                //update image detail
                val entity = DataMapper.mapDomainToEntity(recipes)
                entity.detailImage = data.image
                entity.summary = data.summary
                localDataSource.updateRecipeValue(entity)

                localDataSource.insertIngredients(DataMapper.mapIngredientsResponseToEntity(data.extendedIngredients))
                localDataSource.insertRecipesIngredientCrossRef(data.extendedIngredients.map {
                    RecipesIngredientsCrossRef(recipes.id, it.id)
                })
            }

        }.asFlow()
}