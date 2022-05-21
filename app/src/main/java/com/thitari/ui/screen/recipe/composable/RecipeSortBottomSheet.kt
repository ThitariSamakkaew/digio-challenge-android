package com.thitari.ui.screen.recipe.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thitari.recipedb.data.model.RecipeSortOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SortBottomSheetContent(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    selectedSortOption: RecipeSortOption,
    onSortButtonClick: (RecipeSortOption) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(all = 10.dp)
        ) {

            Text(
                text = "Sort",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .clickable(onClick = {
                        scope.launch { modalBottomSheetState.hide() }
                        onSortButtonClick(RecipeSortOption.NONE)
                    })
                    .align(Alignment.CenterEnd),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                text = "Clear"
            )
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            SortButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                text = "By Name",
                onClick = {
                    scope.launch { modalBottomSheetState.hide() }
                    onSortButtonClick(RecipeSortOption.NAME)
                },
                isSelected = selectedSortOption == RecipeSortOption.NAME
            )

            Spacer(modifier = Modifier.width(16.dp))

            SortButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                text = "By Difficulty",
                onClick = {
                    scope.launch { modalBottomSheetState.hide() }
                    onSortButtonClick(RecipeSortOption.DIFFICULTY)
                },
                isSelected = selectedSortOption == RecipeSortOption.DIFFICULTY
            )
        }
    }
}

@Composable
fun SortButton(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Red else Color.Gray
        ),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {

        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetContentPreview() {
    SortBottomSheetContent(
        onSortButtonClick = {},
        selectedSortOption = RecipeSortOption.NONE,
        modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
        scope = rememberCoroutineScope()
    )
}
