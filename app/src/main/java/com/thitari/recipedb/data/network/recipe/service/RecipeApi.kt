package com.thitari.recipedb.data.network.recipe.service

import com.thitari.recipedb.data.model.RecipeListResult

interface RecipeApi {

    suspend fun getRecipeList(): RecipeListResult
}