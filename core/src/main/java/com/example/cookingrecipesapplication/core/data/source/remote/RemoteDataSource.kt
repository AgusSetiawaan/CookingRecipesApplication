package com.example.cookingrecipesapplication.core.data.source.remote

import android.util.Log
import com.example.cookingrecipesapplication.core.data.source.remote.network.ApiResponse
import com.example.cookingrecipesapplication.core.data.source.remote.network.ApiService
import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesDetailResponse
import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesResponse
import com.example.cookingrecipesapplication.core.data.source.remote.response.SearchRecipesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun searchRecipesByType(type: String, offset: Int, number: Int): Flow<ApiResponse<List<RecipesResponse>>> {
        return flow {
            try {
                val response = apiService.searchRecipes(type = type, offset = offset, numberOfList = number)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                }
                else{
                    emit(ApiResponse.Empty)
                }
            }
            catch (e: Exception){
                emit(ApiResponse.Error(e))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailRecipes(recipesId: Int): Flow<ApiResponse<RecipesDetailResponse>>{
        return flow {
            try {
                val response = apiService.getDetailRecipes(recipesId)
                emit(ApiResponse.Success(response))
            }
            catch (e: Exception){
                emit(ApiResponse.Error(e))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchRecipes(type: String, offset: Int, number: Int): SearchRecipesResponse =
        apiService.searchRecipes(type = type, offset = offset, numberOfList = number)
}