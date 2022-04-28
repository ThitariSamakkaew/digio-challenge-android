package com.thitari.recipedb.data.storage.recipe.mapper

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import javax.inject.Inject

interface RecipeEntityToRecipeMapper {

    fun map(recipeEntity: RecipeEntity): Recipe
}

internal class RecipeEntityToRecipeMapperImpl @Inject constructor() : RecipeEntityToRecipeMapper {

    override fun map(recipeEntity: RecipeEntity): Recipe {

        return Recipe(
            calories = recipeEntity.calories.orEmpty(),
            carbos = recipeEntity.carbos.orEmpty(),
            description = recipeEntity.description.orEmpty(),
            difficulty = recipeEntity.difficulty ?: 0,
            fats = recipeEntity.fats.orEmpty(),
            headline = recipeEntity.headline.orEmpty(),
            id = recipeEntity.id,
            image = recipeEntity.image.orEmpty(),
            name = recipeEntity.name.orEmpty(),
            proteins = recipeEntity.proteins.orEmpty(),
            thumb = recipeEntity.thumb.orEmpty(),
            time = recipeEntity.time.orEmpty()
        )
    }
}
