package com.example.cookingrecipesapplication.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "recipes")
data class RecipesEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val type: String,
    var isFavorite: Boolean,
    var detailImage: String = "",
    var summary: String = ""
)

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey
    val ingredientId: Int,
    val originalName: String,
    val image: String,
    val amount: Double,
    val unit: String,
    val original: String,
    val consitency: String,
    val name: String,
)

@Entity(primaryKeys = ["id", "ingredientId"])
data class RecipesIngredientsCrossRef(
    val id: Int,
    val ingredientId: Int
)

data class RecipesWithIngredients(
    @Embedded
    val recipes: RecipesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipesIngredientsCrossRef::class)
    )
    val ingredients: List<IngredientEntity>
)




