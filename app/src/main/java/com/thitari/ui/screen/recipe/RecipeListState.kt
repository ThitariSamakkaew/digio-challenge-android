package com.thitari.ui.screen.recipe

import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeSortOption

data class RecipeListState(
    val recipes: List<Recipe>,
    val isLoading: Boolean,
    val error: Boolean,
    val favoriteIds: List<String>,
    val sortOption: RecipeSortOption,
) {
    companion object {
        val INITIAL = RecipeListState(
            recipes = emptyList(),
            isLoading = false,
            error = false,
            favoriteIds = emptyList(),
            sortOption = RecipeSortOption.NONE
        )
    }
}
