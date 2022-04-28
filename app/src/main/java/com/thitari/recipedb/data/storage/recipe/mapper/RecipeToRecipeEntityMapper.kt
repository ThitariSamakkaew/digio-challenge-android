package com.thitari.recipedb.data.storage.recipe.mapper

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import javax.inject.Inject

interface RecipeToRecipeEntityMapper {

    fun map(recipe: Recipe): RecipeEntity
}

internal class RecipeToRecipeEntityMapperImpl @Inject constructor(
) : RecipeToRecipeEntityMapper {

    override fun map(recipe: Recipe): RecipeEntity {

        return RecipeEntity(
            id = recipe.id,
            calories = recipe.calories,
            carbos = recipe.carbos,
            description = recipe.description,
            difficulty = recipe.difficulty,
            fats = recipe.fats,
            headline = recipe.headline,
            image = recipe.image,
            name = recipe.name,
            proteins = recipe.proteins,
            thumb = recipe.thumb,
            time = recipe.time
        )
    }
}
