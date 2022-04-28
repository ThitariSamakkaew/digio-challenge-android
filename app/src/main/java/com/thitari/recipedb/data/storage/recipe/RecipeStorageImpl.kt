package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeEntityListToRecipeListResultMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToRecipeEntityMapper
import javax.inject.Inject

internal class RecipeStorageImpl @Inject constructor(
    private val recipeDao: RecipesDao,
    private val recipeEntityListToRecipeListResult: RecipeEntityListToRecipeListResultMapper,
    private val recipeListToRecipeEntityListMapper: RecipeToRecipeEntityMapper
) : RecipeStorage {

    override fun getRecipeList(): RecipeListResult {
        val entities: List<RecipeEntity> = recipeDao.getRecipes()
        return recipeEntityListToRecipeListResult.map(entities)
    }

    override fun insertRecipes(recipes: List<Recipe>) {
        val entities: List<RecipeEntity> =
            recipes.map { recipe -> recipeListToRecipeEntityListMapper.map(recipe) }
        recipeDao.insertRecipes(entities)
    }
}
