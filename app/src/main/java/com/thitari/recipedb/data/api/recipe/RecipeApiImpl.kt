package com.thitari.recipedb.data.api.recipe

import com.thitari.recipedb.data.api.recipe.mapper.RecipeListResponseToRecipeListResultMapper
import com.thitari.recipedb.data.model.RecipeListResult
import javax.inject.Inject

internal class RecipeApiImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeListResponseToRecipeListResultMapper: RecipeListResponseToRecipeListResultMapper
) : RecipeApi {

    override suspend fun getRecipeList(

    ): RecipeListResult {
        return try {
            val response = recipeService.getRecipeList()
            recipeListResponseToRecipeListResultMapper.map(response)
        } catch (e: Exception) {
            RecipeListResult.Error
        }
    }

    companion object {
        const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }
}