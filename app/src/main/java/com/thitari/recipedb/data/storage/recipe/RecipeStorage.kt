package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult

interface RecipeStorage {

    fun getRecipeList(): RecipeListResult
    fun insertRecipes(recipes: List<Recipe>)
}
