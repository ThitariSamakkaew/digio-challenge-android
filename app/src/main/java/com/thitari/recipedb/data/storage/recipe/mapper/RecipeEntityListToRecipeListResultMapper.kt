package com.thitari.recipedb.data.storage.recipe.mapper

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import javax.inject.Inject

interface RecipeEntityListToRecipeListResultMapper {

    fun map(recipeEntities: List<RecipeEntity>): RecipeListResult
}

internal class RecipeEntityListToRecipeListResultImpl @Inject constructor(
    private val recipeEntityToRecipeMapper: RecipeEntityToRecipeMapper
) : RecipeEntityListToRecipeListResultMapper {

    override fun map(recipeEntities: List<RecipeEntity>): RecipeListResult {

        val recipes: List<Recipe> = recipeEntities.map { recipeEntity ->
            recipeEntityToRecipeMapper.map(recipeEntity)

        }
        return RecipeListResult.Success(recipes)
    }
}
