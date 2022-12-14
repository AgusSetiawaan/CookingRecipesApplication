package com.example.cookingrecipesapplication.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    val id: Int,
    val title: String,
    val image: String,
    val type: String,
    val isFavorite: Boolean,
    val detailImage: String = "",
    val summary: String = "",
    val ingredients: List<Ingredient> = listOf()
): Parcelable

@Parcelize
data class Ingredient(
    val originalName: String,
    val image: String,
    val amount: Double,
    val unit: String,
    val original: String,
    val consitency: String,
    val name: String,
    val id: Int,
): Parcelable
