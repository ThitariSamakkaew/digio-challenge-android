package com.thitari.ui.screen.recipe

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
fun FavoriteButtonPreview() {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }
    FavoriteButton(
        isChecked = isChecked,
        onChecked = { setChecked(!isChecked) }
    )
}
