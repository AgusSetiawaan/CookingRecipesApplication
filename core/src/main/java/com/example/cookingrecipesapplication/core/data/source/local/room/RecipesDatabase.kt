package com.example.cookingrecipesapplication.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookingrecipesapplication.core.data.source.local.entity.IngredientEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesIngredientsCrossRef
import com.example.cookingrecipesapplication.core.data.source.local.entity.RemoteKeys

@Database(entities = [RecipesEntity::class, RemoteKeys::class, IngredientEntity::class, RecipesIngredientsCrossRef::class], version = 1, exportSchema = false)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}