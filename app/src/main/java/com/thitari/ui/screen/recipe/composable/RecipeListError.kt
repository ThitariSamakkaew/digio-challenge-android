package com.thitari.ui.screen.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thitari.recipesdb.R

@Composable
fun RecipeListError(
    onTryAgainClick: () -> Unit,
) {

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
            onClick = onTryAgainClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {

            Text(text = stringResource(R.string.recipe_list_error_button))
        }
    }
}

@Preview
@Composable
fun RecipeListErrorPreview() {
    RecipeListError(onTryAgainClick = {})
}
