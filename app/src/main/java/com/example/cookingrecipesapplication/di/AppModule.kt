package com.example.cookingrecipesapplication.di

import com.example.cookingrecipesapplication.core.domain.usecase.RecipesInteractor
import com.example.cookingrecipesapplication.core.domain.usecase.RecipesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(recipesInteractor: RecipesInteractor): RecipesUseCase
}