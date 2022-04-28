package com.thitari.recipedb.data.storage.recipe.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_table")
data class RecipeEntity(
    @PrimaryKey @ColumnInfo("id") val id: String,
    @ColumnInfo(name = "calories") val calories: String?,
    @ColumnInfo(name = "carbos") val carbos: String?,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("difficulty") val difficulty: Int?,
    @ColumnInfo("fats") val fats: String?,
    @ColumnInfo("headline") val headline: String?,
    @ColumnInfo("image") val image: String?,
    @ColumnInfo("name") val name: String?,
    @ColumnInfo("proteins") val proteins: String?,
    @ColumnInfo("thumb") val thumb: String?,
    @ColumnInfo("time") val time: String?,
)
