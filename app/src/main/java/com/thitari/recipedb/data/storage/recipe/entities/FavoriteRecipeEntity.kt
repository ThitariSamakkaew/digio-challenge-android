package com.thitari.recipedb.data.storage.recipe.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipe_table")
data class FavoriteRecipeEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
)