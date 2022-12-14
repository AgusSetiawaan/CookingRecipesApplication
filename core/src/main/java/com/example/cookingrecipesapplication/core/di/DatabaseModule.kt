package com.example.cookingrecipesapplication.core.di

import android.content.Context
import androidx.room.Room
import com.example.cookingrecipesapplication.core.data.source.local.room.RecipesDao
import com.example.cookingrecipesapplication.core.data.source.local.room.RecipesDatabase
import com.example.cookingrecipesapplication.core.data.source.local.room.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRecipesDatabase(@ApplicationContext context: Context): RecipesDatabase = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java, "Recipes.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRecipesDao(database: RecipesDatabase): RecipesDao = database.recipesDao()

    @Provides
    fun provideRemoteKeysDao(database: RecipesDatabase): RemoteKeysDao = database.remoteKeysDao()
}