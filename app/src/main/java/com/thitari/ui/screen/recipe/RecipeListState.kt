package com.thitari.ui.screen.recipe

import com.thitari.recipedb.data.model.Recipe

data class RecipeListState(
    val recipes: List<Recipe>,
    val isLoading: Boolean,
    val error: Boolean,
    val favoriteIds: List<String>
) {
    companion object {
        val INITIAL = RecipeListState(
            recipes = emptyList(),
            isLoading = false,
            error = false,
            favoriteIds = emptyList()
        )
    }
}
