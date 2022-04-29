package com.thitari.recipedb.data.api.recipe.mapper

import com.thitari.recipedb.data.api.recipe.RecipeApi
import com.thitari.recipedb.data.api.recipe.RecipeApiImpl
import com.thitari.recipedb.data.api.recipe.RecipeService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindRecipeApi(impl: RecipeApiImpl): RecipeApi

    @Singleton
    @Binds
    abstract fun bindRecipeListResponseToRecipeListResultMapper(
        impl: RecipeListResponseToRecipeListResultMapperImpl
    ): RecipeListResponseToRecipeListResultMapper

    @Singleton
    @Binds
    abstract fun bindRecipeResponseToRecipeMapper(impl: RecipeResponseToRecipeMapper):
            RecipeResponseToRecipeMapper

    companion object {

        @Singleton
        @Provides
        fun provideRecipeService(): RecipeService = Retrofit
            .Builder()
            .baseUrl(RecipeApiImpl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }
}
