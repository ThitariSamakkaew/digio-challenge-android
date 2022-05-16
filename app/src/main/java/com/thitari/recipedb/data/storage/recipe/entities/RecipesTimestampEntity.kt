package com.thitari.recipedb.data.storage.recipe.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_timestamp_table")
data class RecipesTimestampEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = ID,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
) {
    companion object {
         val ID = 1
    }
}