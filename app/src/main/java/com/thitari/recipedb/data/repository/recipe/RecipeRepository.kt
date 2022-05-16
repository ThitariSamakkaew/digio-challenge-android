package com.thitari.recipedb.data.repository.recipe

import com.thitari.recipedb.data.api.recipe.RecipeApi
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.storage.recipe.RecipeStorage
import javax.inject.Inject

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun refreshRecipes(): List<Recipe>
    suspend fun addRecipeToFavorite(recipe: Recipe)
    suspend fun removeRecipeFromFavorite(recipe: Recipe)
    suspend fun getFavoriteRecipeIds(): List<String>
    suspend fun getOrRefreshRecipesByTime(time: Long): List<Recipe>
}

internal class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeStorage: RecipeStorage,
) : RecipeRepository {

    override suspend fun getOrRefreshRecipesByTime(time: Long): List<Recipe> {
        val timestamp = recipeStorage.getRecipeTimestamp()
        val timeSpent = System.currentTimeMillis() - timestamp
        return if (timeSpent > time) {
            refreshRecipes()
        } else {
            getRecipes()
        }
    }

    override suspend fun getRecipes(): List<Recipe> {
        var recipes = recipeStorage.getRecipes()
        if (recipes.isEmpty()) {
            recipes = refreshRecipes()
        }
        return recipes
    }

    override suspend fun refreshRecipes(): List<Recipe> {
        val recipesResult = recipeApi.getRecipes()
        if (recipesResult.isNotEmpty()) {
            recipeStorage.insertRecipes(recipes = recipesResult)
        }
        return recipesResult
    }

    override suspend fun addRecipeToFavorite(recipe: Recipe) {
        recipeStorage.addRecipesToFavorite(recipe = recipe)
    }

    override suspend fun removeRecipeFromFavorite(recipe: Recipe) {
        recipeStorage.deleteRecipeFromFavorite(recipe = recipe)
    }

    override suspend fun getFavoriteRecipeIds(): List<String> {
        return recipeStorage.getFavoriteRecipeIds()
    }
}
