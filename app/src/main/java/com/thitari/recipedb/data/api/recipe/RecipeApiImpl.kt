package com.thitari.recipedb.data.api.recipe

import com.thitari.recipedb.data.api.recipe.mapper.RecipeResponseToRecipeMapper
import com.thitari.recipedb.data.api.recipe.response.RecipeResponse
import com.thitari.recipedb.data.model.Recipe
import javax.inject.Inject

internal class RecipeApiImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeResponseToRecipeMapper: RecipeResponseToRecipeMapper,
) : RecipeApi {

    override suspend fun getRecipes(): List<Recipe> {
        val responses: List<RecipeResponse> = recipeService.getRecipes()
        val recipes: List<Recipe> = responses.map { response ->
            recipeResponseToRecipeMapper.map(response)
        }
        return recipes
    }

    companion object {
        const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }
}
