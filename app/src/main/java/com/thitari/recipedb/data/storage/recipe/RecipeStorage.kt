package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe

interface RecipeStorage {

    suspend fun getRecipes(): List<Recipe>
    suspend fun insertRecipes(recipes: List<Recipe>)
    suspend fun addRecipesToFavorite(recipe: Recipe)
    suspend fun deleteRecipeFromFavorite(recipe: Recipe)
    suspend fun getFavoriteRecipeIds(): List<String>
    suspend fun getRecipeTimestamp(): Long
}
