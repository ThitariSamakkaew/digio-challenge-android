package com.thitari.recipedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipesdb.R
import com.thitari.ui.screen.recipe.RecipeItem
import com.thitari.ui.screen.recipe.RecipeListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeListScreen(recipes = emptyList())
        }
    }
}
