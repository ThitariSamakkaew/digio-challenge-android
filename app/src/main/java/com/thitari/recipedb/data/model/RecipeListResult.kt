package com.thitari.recipedb.data.model

sealed class RecipeListResult {

    data class Success(val recipes: List<Recipe>) : RecipeListResult()

    object Error : RecipeListResult()

}