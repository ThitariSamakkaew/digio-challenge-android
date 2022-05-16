package com.thitari.recipedb.data.api.recipe

import com.thitari.recipedb.data.model.Recipe

interface RecipeApi {

    suspend fun getRecipes(): List<Recipe>
}
