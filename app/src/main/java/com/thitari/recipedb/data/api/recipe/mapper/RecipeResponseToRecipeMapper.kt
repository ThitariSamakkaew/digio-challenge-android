package com.thitari.recipedb.data.api.recipe.mapper

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.api.recipe.response.RecipeResponse
import javax.inject.Inject

interface RecipeResponseToRecipeMapper {
    fun map(response: RecipeResponse): Recipe
}

internal class RecipeMapperToRecipeImpl @Inject constructor() : RecipeResponseToRecipeMapper {

    override fun map(response: RecipeResponse): Recipe {

        return Recipe(
            calories = response.calories.orEmpty(),
            carbos = response.carbos.orEmpty(),
            description = response.description.orEmpty(),
            difficulty = response.difficulty ?: 0,
            fats = response.fats.orEmpty(),
            headline = response.headline.orEmpty(),
            id = response.id.orEmpty(),
            image = response.image.orEmpty(),
            name = response.name.orEmpty(),
            proteins = response.proteins.orEmpty(),
            thumb = response.thumb.orEmpty(),
            time = response.time.orEmpty()
        )
    }
}
