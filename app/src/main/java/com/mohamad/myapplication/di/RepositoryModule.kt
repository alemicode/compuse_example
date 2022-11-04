package com.mohamad.myapplication.di


import com.mohamad.myapplication.network.RecipeService
import com.mohamad.myapplication.network.model.RecipeDtoMapper
import com.mohamad.myapplication.repository.RecipeRepository
import com.mohamad.myapplication.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}

