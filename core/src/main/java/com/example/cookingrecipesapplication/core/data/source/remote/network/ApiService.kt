package com.example.cookingrecipesapplication.core.data.source.remote.network

import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesDetailResponse
import com.example.cookingrecipesapplication.core.data.source.remote.response.SearchRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String? = null,
        @Query("type") type: String? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") numberOfList: Int? = null
    ): SearchRecipesResponse

    @GET("recipes/{id}/information")
    suspend fun getDetailRecipes(
        @Path("id") recipesId: Int
    ): RecipesDetailResponse
}