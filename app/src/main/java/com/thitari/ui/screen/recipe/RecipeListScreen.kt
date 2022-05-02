package com.thitari.ui.screen.recipe

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipesdb.R

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(recipes) { recipe -> RecipeItem(recipe) }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.thumb)
                    .build(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxHeight(),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = recipe.name,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = recipe.headline,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                NutritionText(
                    nameRes = R.string.recipe_nutrition_calories,
                    nutrition = recipe.calories
                )

                NutritionText(
                    nameRes = R.string.recipe_nutrition_fat,
                    nutrition = recipe.fats,
                )

                NutritionText(
                    nameRes = R.string.recipe_nutrition_carbos,
                    nutrition = recipe.carbos,
                )

                NutritionText(
                    nameRes = R.string.recipe_nutrition_proteins,
                    nutrition = recipe.proteins,
                )

                NutritionText(
                    nameRes = R.string.recipe_nutrition_difficulty,
                    nutrition = recipe.fats,
                )
            }
        }
    }
}

@Composable
fun NutritionText(
    @StringRes nameRes: Int,
    nutrition: String
) {
    Row(modifier = Modifier.padding(top = 2.dp)) {
        Text(
            text = stringResource(id = nameRes),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = nutrition,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview
@Composable
fun RecipeListPreview() {
    val recipes = mutableListOf<Recipe>()
    for (i in 0..100) {
        recipes.add(
            Recipe(
                calories = "516 kcal",
                carbos = "47 g",
                description = "description",
                difficulty = 0,
                fats = "8 g",
                headline = "with Sweet Potato Wedges and Minted Snap Peas",
                id = "533143aaff604d567f8b4571",
                image = "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
                name = "Crispy Fish Goujons ",
                proteins = "43 g",
                thumb = "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
                time = "PT35M"
            )
        )
    }
    RecipeListScreen(recipes = recipes)
}
