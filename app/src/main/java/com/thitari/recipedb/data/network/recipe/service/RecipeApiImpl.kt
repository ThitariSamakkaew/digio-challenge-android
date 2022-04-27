package com.thitari.recipedb.data.network.recipe.service

import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.network.recipe.mapper.RecipeListResponseToRecipeListResultMapper
import javax.inject.Inject

internal class RecipeApiImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeListResponseToRecipeListResultMapper: RecipeListResponseToRecipeListResultMapper
) : RecipeApi {

    override suspend fun getRecipeList(): RecipeListResult {
        val response = recipeService.getRecipeList()
        return recipeListResponseToRecipeListResultMapper.map(response)
    }

    companion object {
        const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }
}