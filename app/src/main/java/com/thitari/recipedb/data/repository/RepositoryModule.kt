package com.thitari.recipedb.data.repository

import com.thitari.recipedb.data.repository.recipe.RecipeRepository
import com.thitari.recipedb.data.repository.recipe.RecipeRepositoryImpl
import com.thitari.recipedb.viewModel.recipe.RecipeViewModel
import com.thitari.recipedb.viewModel.recipe.RecipeViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository
}

//
//@Module
//@InstallIn(SingletonComponent::class)
//internal abstract class NetworkModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindRecipeApi(impl: RecipeApiImpl): RecipeApi
//
//    @Singleton
//    @Binds
//    abstract fun bindRecipeListResponseToRecipeListResultMapper(
//        impl: RecipeListResponseToRecipeListResultMapperImpl
//    ): RecipeListResponseToRecipeListResultMapper
//
//    @Singleton
//    @Binds
//    abstract fun bindRecipeResponseToRecipeMapper(impl: RecipeResponseToRecipeMapper):
//            RecipeResponseToRecipeMapper
//
//    companion object {
//
//        @Singleton
//        @Provides
//        fun provideRecipeService(): RecipeService = Retrofit
//            .Builder()
//            .baseUrl(RecipeApiImpl.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(RecipeService::class.java)
//    }
//}
