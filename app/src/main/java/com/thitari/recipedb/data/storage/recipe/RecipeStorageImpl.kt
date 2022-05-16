package com.thitari.recipedb.data.storage.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.storage.recipe.entities.FavoriteRecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipesTimestampEntity
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeEntityToRecipeMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToFavoriteRecipeEntityMapper
import com.thitari.recipedb.data.storage.recipe.mapper.RecipeToRecipeEntityMapper
import javax.inject.Inject

internal class RecipeStorageImpl @Inject constructor(
    private val recipeDao: RecipesDao,
    private val recipeEntityToRecipeMapper: RecipeEntityToRecipeMapper,
    private val recipeListToRecipeEntityListMapper: RecipeToRecipeEntityMapper,
    private val recipeToFavoriteRecipeEntityMapper: RecipeToFavoriteRecipeEntityMapper,
) : RecipeStorage {

    override suspend fun getRecipes(): List<Recipe> {
        val entities: List<RecipeEntity> = recipeDao.getRecipes()
        val recipes = entities.map { entity ->
            recipeEntityToRecipeMapper.map(entity)
        }
        return recipes
    }

    override suspend fun insertRecipes(recipes: List<Recipe>) {
        val entities: List<RecipeEntity> = recipes.map { recipe: Recipe ->
            recipeListToRecipeEntityListMapper.map(recipe)
        }
        recipeDao.insertRecipes(entities)

        val recipesTimestampEntity = RecipesTimestampEntity(
            timestamp = System.currentTimeMillis()
        )
        recipeDao.insertRecipesTimestamp(recipesTimestampEntity)
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

    override suspend fun getRecipeTimestamp(): Long {
        val entity: RecipesTimestampEntity? = recipeDao.getRecipesTimestamp()
        return entity?.timestamp ?: 0L
    }
}
