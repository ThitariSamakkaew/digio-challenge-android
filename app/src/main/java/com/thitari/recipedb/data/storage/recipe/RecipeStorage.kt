package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult

interface RecipeStorage {

    suspend fun getRecipeList(): RecipeListResult
    suspend fun insertRecipes(recipes: List<Recipe>)
    suspend fun addRecipesToFavorite(recipe: Recipe)
    suspend fun deleteRecipeFromFavorite(recipe: Recipe)
    suspend fun getFavoriteRecipeIds(): List<String>
}
