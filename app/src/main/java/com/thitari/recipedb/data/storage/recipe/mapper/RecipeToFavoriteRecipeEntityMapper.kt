package com.thitari.recipedb.data.storage.recipe.mapper

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.storage.recipe.entities.FavoriteRecipeEntity
import javax.inject.Inject

interface RecipeToFavoriteRecipeEntityMapper {
    fun map(favorite: Recipe): FavoriteRecipeEntity
}

internal class RecipeToFavoriteRecipeEntityMapperImpl @Inject constructor() :
    RecipeToFavoriteRecipeEntityMapper {

    override fun map(favorite: Recipe): FavoriteRecipeEntity {
        return FavoriteRecipeEntity(
            id = favorite.id
        )
    }
}
