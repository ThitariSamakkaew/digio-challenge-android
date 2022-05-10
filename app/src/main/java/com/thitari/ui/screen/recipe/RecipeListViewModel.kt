package com.thitari.ui.screen.recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.model.RecipeListResult
import com.thitari.recipedb.data.repository.recipe.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository,
) : ViewModel() {

    var state by mutableStateOf(RecipeListState.INITIAL)
        private set

    val onAddToFavorite: (Recipe) -> Unit = { recipe ->
        viewModelScope.launch {
            recipesRepository.addRecipeToFavorite(recipe = recipe)

            val updateFavoriteIds = recipesRepository.getFavoriteRecipeIds()
            state = state.copy(
                favoriteIds = updateFavoriteIds
            )
        }
    }

    val onRemoveFromFavorite: (Recipe) -> Unit = { recipe ->
        viewModelScope.launch {
            recipesRepository.removeRecipeFromFavorite(recipe = recipe)

            val updateFavoriteIds = recipesRepository.getFavoriteRecipeIds()
            state = state.copy(
                favoriteIds = updateFavoriteIds
            )
        }
    }

    init {
        viewModelScope.launch {
            flow {
                emit(recipesRepository.getRecipes())
            }.combine(
                flow {
                    emit(recipesRepository.getFavoriteRecipeIds())
                }
            ) { recipesResult, favoriteIds ->
                recipesResult to favoriteIds
            }.onStart {
                val newState = state.copy(isLoading = true)
                state = newState
            }.catch {
                val newState = state.copy(
                    isLoading = false,
                    error = true
                )
                state = newState
            }.collect { (recipesResult, favoriteIds) ->
                val newState = when (recipesResult) {
                    is RecipeListResult.Success -> state.copy(
                        isLoading = false,
                        error = false,
                        recipes = recipesResult.recipes,
                        favoriteIds = favoriteIds
                    )
                    is RecipeListResult.Error -> state.copy(
                        isLoading = false,
                        error = false
                    )
                }
                state = newState
            }
        }
    }
}
