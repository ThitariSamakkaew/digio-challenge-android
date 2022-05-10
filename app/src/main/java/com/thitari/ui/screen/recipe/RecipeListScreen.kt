package com.thitari.ui.screen.recipe

import androidx.annotation.StringRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipesdb.R

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    when {
        state.isLoading -> RecipeListLoading()
        state.error -> RecipeListError()
        else -> RecipeListContent(
            recipes = state.recipes,
            onAddToFavorite = viewModel.onAddToFavorite,
            onRemoveFromFavorite = viewModel.onRemoveFromFavorite,
            favoriteIds = state.favoriteIds
        )
    }
}

@Composable
fun RecipeListLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.recipe_list_loading),
            fontSize = 16.sp,
            modifier = Modifier.padding(all = 10.dp)
        )

        CircularProgressIndicator(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
        )
    }
}

@Composable
fun RecipeListError() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.ic_dissatisfied),
            contentDescription = stringResource(R.string.recipe_list_error_message),
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )

        Text(
            text = stringResource(R.string.recipe_list_error_message),
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )

        Button(
            onClick = {

            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.recipe_list_error_button))
        }
    }
}

@Composable
fun RecipeListContent(
    recipes: List<Recipe>,
    onAddToFavorite: (Recipe) -> Unit,
    onRemoveFromFavorite: (Recipe) -> Unit,
    favoriteIds: List<String>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onAddToFavorite = onAddToFavorite,
                onRemoveFromFavorite = onRemoveFromFavorite,
                favoriteIds = favoriteIds
            )
        }
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onAddToFavorite: (Recipe) -> Unit,
    onRemoveFromFavorite: (Recipe) -> Unit,
    favoriteIds: List<String>,
) {
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
                    nameRes = R.string.recipe_list_item_nutrition_calories,
                    nutrition = recipe.calories
                )

                NutritionText(
                    nameRes = R.string.recipe_list_item_nutrition_fat,
                    nutrition = recipe.fats,
                )

                NutritionText(
                    nameRes = R.string.recipe_list_item_nutrition_carbos,
                    nutrition = recipe.carbos,
                )

                NutritionText(
                    nameRes = R.string.recipe_list_item_nutrition_proteins,
                    nutrition = recipe.proteins,
                )

                NutritionText(
                    nameRes = R.string.recipe_list_item_nutrition_difficulty,
                    nutrition = recipe.fats,
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End,
                ) {
                    FavoriteButton(
                        isChecked = favoriteIds.contains(recipe.id),
                        onChecked = { isChecked ->
                            if (isChecked) {
                                onAddToFavorite(recipe)
                            } else {
                                onRemoveFromFavorite(recipe)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NutritionText(
    @StringRes nameRes: Int,
    nutrition: String,
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

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    IconToggleButton(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onChecked
    ) {
        val transition = updateTransition(isChecked, label = "Checked indicator")

        val tint by transition.animateColor(
            label = "Tint"
        ) { isChecked ->
            if (isChecked) Color.Red else Color.Black
        }
        Icon(
            imageVector = if (isChecked) {
                Icons.Filled.Favorite
            } else {
                Icons.Filled.FavoriteBorder
            },
            contentDescription = null,
            tint = tint
        )
    }
}

@Preview
@Composable
fun RecipeListErrorPreview() {
    RecipeListError()
}

@Preview
@Composable
fun RecipeListLoadingPreview() {
    RecipeListLoading()
}

@Preview
@Composable
fun RecipeListPreview() {
    val recipes = mutableListOf<Recipe>()
    for (i in 0..100) {
        recipes.add(recipe)
    }
    RecipeListContent(
        recipes = recipes,
        onAddToFavorite = {},
        onRemoveFromFavorite = {},
        favoriteIds = emptyList())
}

@Preview
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipe = recipe,
        onAddToFavorite = {},
        onRemoveFromFavorite = {},
        favoriteIds = emptyList()
    )
}

private val recipe = Recipe(
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

@Preview
@Composable
fun FavoriteButtonPreview() {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }
    FavoriteButton(
        isChecked = isChecked,
        onChecked = { setChecked(!isChecked) }
    )
}
