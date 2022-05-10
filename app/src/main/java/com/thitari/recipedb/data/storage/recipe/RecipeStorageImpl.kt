package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.storage.recipe.entities.FavoriteRecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeEntityListToRecipeListResultMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToFavoriteRecipeEntityMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToRecipeEntityMapper
import javax.inject.Inject

internal class RecipeStorageImpl @Inject constructor(
    private val recipeDao: RecipesDao,
    private val recipeEntityListToRecipeListResult: RecipeEntityListToRecipeListResultMapper,
    private val recipeListToRecipeEntityListMapper: RecipeToRecipeEntityMapper,
    private val recipeToFavoriteRecipeEntityMapper: RecipeToFavoriteRecipeEntityMapper,
) : RecipeStorage {

    override suspend fun getRecipeList(): RecipeListResult {
        val entities: List<RecipeEntity> = recipeDao.getRecipes()
        return recipeEntityListToRecipeListResult.map(entities)
    }

    override suspend fun insertRecipes(recipes: List<Recipe>) {
        val entities: List<RecipeEntity> = recipes.map { recipe: Recipe ->
            recipeListToRecipeEntityListMapper.map(recipe)
        }
        recipeDao.insertRecipes(entities)
    }

    override suspend fun addRecipesToFavorite(recipe: Recipe) {
        val entity: FavoriteRecipeEntity = recipeToFavoriteRecipeEntityMapper.map(recipe)
        recipeDao.insertRecipeFavorite(entity)
    }

    override suspend fun deleteRecipeFromFavorite(recipe: Recipe) {
        val entity: FavoriteRecipeEntity = recipeToFavoriteRecipeEntityMapper.map(recipe)
        recipeDao.deleteFavoriteRecipe(entity)
    }

    override suspend fun getFavoriteRecipeIds(): List<String> {
        val entities: List<FavoriteRecipeEntity> = recipeDao.getFavoriteRecipes()
        val ids: List<String> = entities.map { entity -> entity.id }
        return ids
    }
}
