package com.thitari.recipedb.data.network.recipe.mapper

import com.thitari.recipedb.data.network.recipe.service.RecipeApi
import com.thitari.recipedb.data.network.recipe.service.RecipeApiImpl
import com.thitari.recipedb.data.network.recipe.service.RecipeService
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
