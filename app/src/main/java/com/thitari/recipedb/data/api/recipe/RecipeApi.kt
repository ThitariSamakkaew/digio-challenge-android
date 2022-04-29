package com.thitari.recipedb.data.api.recipe

import com.thitari.recipedb.data.model.RecipeListResult

interface RecipeApi {

    suspend fun getRecipeList(): RecipeListResult
}
