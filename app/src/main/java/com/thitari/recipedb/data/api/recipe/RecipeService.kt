package com.thitari.recipedb.data.api.recipe

import com.thitari.recipedb.data.api.recipe.response.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface RecipeService {

    @GET("android-test/recipes.json")
    suspend fun getRecipeList(): Response<List<RecipeResponse>>
}