package com.example.cookingrecipesapplication.core.di

import com.example.cookingrecipesapplication.core.data.RecipesRepository
import com.example.cookingrecipesapplication.core.domain.repository.IRecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(recipesRepository: RecipesRepository): IRecipesRepository
}