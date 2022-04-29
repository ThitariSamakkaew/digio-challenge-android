package com.thitari.recipedb.data.api.recipe.mapper

import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.api.recipe.response.RecipeResponse
import retrofit2.Response
import javax.inject.Inject

interface RecipeListResponseToRecipeListResultMapper {
    fun map(response: Response<List<RecipeResponse>>): RecipeListResult
}

internal class RecipeListResponseToRecipeListResultMapperImpl @Inject constructor(
    private val recipeResponseToRecipeMapper: RecipeResponseToRecipeMapper
) : RecipeListResponseToRecipeListResultMapper {

    override fun map(response: Response<List<RecipeResponse>>): RecipeListResult {
        return if (response.isSuccessful) {
            val recipes = response.body()
                ?.map { recipeResponse -> recipeResponseToRecipeMapper.map(recipeResponse) }
                ?: emptyList()
            RecipeListResult.Success(recipes)
        } else {
            RecipeListResult.Error
        }
    }
}

