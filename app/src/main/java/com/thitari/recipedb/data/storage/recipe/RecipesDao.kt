package com.thitari.recipedb.data.storage.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thitari.recipedb.data.storage.recipe.entities.FavoriteRecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    suspend fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM favorite_recipe_table")
    suspend fun getFavoriteRecipes(): List<FavoriteRecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeFavorite(recipes: FavoriteRecipeEntity)

    @Delete
    suspend fun deleteFavoriteRecipe(recipes: FavoriteRecipeEntity)
}
