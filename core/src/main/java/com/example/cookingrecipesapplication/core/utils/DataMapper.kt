package com.example.cookingrecipesapplication.core.utils

import com.example.cookingrecipesapplication.core.data.source.local.entity.IngredientEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesWithIngredients
import com.example.cookingrecipesapplication.core.data.source.remote.response.ExtendedIngredientsItem
import com.example.cookingrecipesapplication.core.data.source.remote.response.RecipesResponse
import com.example.cookingrecipesapplication.core.domain.model.Ingredient
import com.example.cookingrecipesapplication.core.domain.model.Recipes

object DataMapper {
    fun mapResponsesToEntities(input: List<RecipesResponse>, type: String): List<RecipesEntity> {
        return input.map {
            RecipesEntity(
                id = it.id,
                title = it.title,
                image = it.image,
                type = type,
                isFavorite = false
            )
        }
    }

    fun mapIngredientsResponseToEntity(input: List<ExtendedIngredientsItem>): List<IngredientEntity> {
        return input.map {
            IngredientEntity(
                ingredientId = it.id,
                originalName = it.originalName,
                image = it.image?:"",
                amount = it.amount,
                original = it.original,
                unit = it.unit,
                consitency = it.consitency?:"",
                name = it.name
            )
        }
    }

    fun mapEntitiesToDomain(input: List<RecipesEntity>): List<Recipes> =
        input.map {
            Recipes(
                id = it.id,
                title = it.title,
                image = it.image,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    private fun mapIngredientsEntityToDomain(input: List<IngredientEntity>): List<Ingredient> =
        input.map {
            Ingredient(
                originalName = it.originalName,
                original = it.original,
                image = it.image,
                amount = it.amount,
                unit = it.unit,
                consitency = it.consitency,
                name = it.name,
                id = it.ingredientId
            )
        }

    fun mapEntityToDomain(input: RecipesWithIngredients) = Recipes(
        id = input.recipes.id,
        title = input.recipes.title,
        image = input.recipes.image,
        type = input.recipes.type,
        isFavorite = input.recipes.isFavorite,
        ingredients = mapIngredientsEntityToDomain(input.ingredients),
        detailImage = input.recipes.detailImage,
        summary = input.recipes.summary
    )

    fun mapDomainToEntity(input: Recipes) = RecipesEntity(
        id = input.id,
        title = input.title,
        image = input.image,
        type = input.type,
        isFavorite = input.isFavorite,
        detailImage = input.detailImage,
        summary = input.summary
    )
}