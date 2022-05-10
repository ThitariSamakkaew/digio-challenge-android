package com.thitari.recipedb.data.repository

import com.thitari.recipedb.data.api.NetworkModule
import com.thitari.recipedb.data.repository.recipe.RecipeRepository
import com.thitari.recipedb.data.repository.recipe.RecipeRepositoryImpl
import com.thitari.recipedb.data.storage.StorageModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        StorageModule::class
    ]
)
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository
}
