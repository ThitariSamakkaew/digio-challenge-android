package com.thitari.recipedb.data.storage

import android.content.Context
import androidx.room.Room
import com.thitari.recipedb.data.storage.recipe.RecipeStorage
import com.thitari.recipedb.data.storage.recipe.RecipeStorageImpl
import com.thitari.recipedb.data.storage.recipe.RecipesDatabase
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeEntityToRecipeMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeEntityToRecipeMapperImpl
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToFavoriteRecipeEntityMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToFavoriteRecipeEntityMapperImpl
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToRecipeEntityMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToRecipeEntityMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StorageModule {

    @Singleton
    @Binds
    abstract fun bindRecipeEntityToRecipeMapper(
        impl: RecipeEntityToRecipeMapperImpl,
    ): RecipeEntityToRecipeMapper

    @Singleton
    @Binds
    abstract fun bindRecipeToRecipeEntityMapper(
        impl: RecipeToRecipeEntityMapperImpl,
    ): RecipeToRecipeEntityMapper

    @Singleton
    @Binds
    abstract fun bindRecipeToFavoriteEntityMapper(
        impl: RecipeToFavoriteRecipeEntityMapperImpl,
    ): RecipeToFavoriteRecipeEntityMapper

    @Singleton
    @Binds
    abstract fun bindRecipeStorage(
        impl: RecipeStorageImpl,
    ): RecipeStorage

    companion object {

        @Singleton
        @Provides
        fun provideRecipesDao(
            database: RecipesDatabase,
        ) = database.recipesDao()

        @Singleton
        @Provides
        fun provideRecipesDatabase(
            @ApplicationContext context: Context,
        ): RecipesDatabase = Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            RecipesDatabase.NAME
        ).build()
    }
}
