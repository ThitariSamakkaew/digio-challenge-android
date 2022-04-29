package com.thitari.recipedb.data.repository.recipe

import com.thitari.recipedb.data.api.recipe.RecipeApi
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.storage.recipe.RecipeStorage
import javax.inject.Inject

interface RecipeRepository {
    suspend fun getRecipes(): RecipeListResult
}

internal class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeStorage: RecipeStorage
) : RecipeRepository {

    override suspend fun getRecipes(): RecipeListResult {
        var result = recipeStorage.getRecipeList()
        if (result is RecipeListResult.Error ||
            result is RecipeListResult.Success && result.recipes.isEmpty()
        ) {
            result = recipeApi.getRecipeList()
            if (result is RecipeListResult.Success) {
                recipeStorage.insertRecipes(result.recipes)
            }
        }
        return result
    }
}
