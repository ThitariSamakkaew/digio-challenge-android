package com.thitari.recipedb.data.storage.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getRecipes(): List<RecipeEntity>

    @Insert
    fun insertRecipes(recipes: List<RecipeEntity>)

}


