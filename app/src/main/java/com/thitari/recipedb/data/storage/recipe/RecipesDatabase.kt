package com.thitari.recipedb.data.storage.recipe

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thitari.recipedb.data.storage.recipe.entities.FavoriteRecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipeEntity
import com.thitari.recipedb.data.storage.recipe.entities.RecipesTimestampEntity

@Database(
    entities = [
        RecipeEntity::class,
        FavoriteRecipeEntity::class,
        RecipesTimestampEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

    companion object {
        const val NAME = "recipe_database"
    }
}
