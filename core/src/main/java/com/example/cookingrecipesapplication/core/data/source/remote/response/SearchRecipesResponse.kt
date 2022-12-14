package com.example.cookingrecipesapplication.core.data.source.remote.response

data class SearchRecipesResponse(
    val results: List<RecipesResponse>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)

data class RecipesResponse(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String
)