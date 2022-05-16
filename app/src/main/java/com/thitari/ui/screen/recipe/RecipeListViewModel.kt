package com.thitari.ui.screen.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thitari.recipedb.data.model.Recipe
import com.thitari.recipedb.data.repository.recipe.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository,
) : ViewModel() {

    val state: StateFlow<RecipeListState>
        get() = _state

    private val _state = MutableStateFlow(RecipeListState.INITIAL)

    private val jobs: MutableList<Job> = mutableListOf()

    val onAddToFavorite: (Recipe) -> Unit = { recipe ->
        viewModelScope.launch {
            recipesRepository.addRecipeToFavorite(recipe = recipe)
            val updatedFavoriteIds: List<String> = recipesRepository.getFavoriteRecipeIds()
            _state.value = _state.value.copy(
                favoriteIds = updatedFavoriteIds
            )
        }
    }

    val onRemoveFromFavorite: (Recipe) -> Unit = { recipe ->
        viewModelScope.launch {
            recipesRepository.removeRecipeFromFavorite(recipe = recipe)
            val updatedFavoriteIds = recipesRepository.getFavoriteRecipeIds()
            _state.value = _state.value.copy(
                favoriteIds = updatedFavoriteIds
            )
        }
    }

    val onTryAgainClick: () -> Unit = {
        fetchRecipes()
    }
    val onStart: () -> Unit = {
        fetchRecipes()
    }
    val onStop: () -> Unit = {
        jobs.forEach { job -> job.cancel() }
        jobs.clear()
    }

    private fun fetchRecipes() {
        val job: Job = viewModelScope.launch {
            flow {
                emit(recipesRepository.getOrRefreshRecipesByTime(REFRESH_TIME))
            }.combine(
                flow {
                    emit(recipesRepository.getFavoriteRecipeIds())
                }
            ) { recipes: List<Recipe>, favoriteIds: List<String> ->
                recipes to favoriteIds
            }.onStart {
                val newState = _state.value.copy(isLoading = true)
                _state.value = newState
            }.catch { error ->
                println(error)
                val newState = _state.value.copy(
                    isLoading = false,
                    error = true
                )
                _state.value = newState
            }.cancellable()
                .collect { (recipes: List<Recipe>, favoriteIds: List<String>) ->
                    val newState = _state.value.copy(
                        recipes = recipes,
                        isLoading = false,
                        error = false,
                        favoriteIds = favoriteIds
                    )
                    _state.value = newState
                    refreshRecipes()
                }
        }
        jobs.add(job)
    }

    private fun refreshRecipes() {
        val job: Job = viewModelScope.launch {
            flow {
                repeat(Int.MAX_VALUE) {
                    delay(REFRESH_TIME)
                    emit(recipesRepository.refreshRecipes())
                }
            }.combine(
                flow {
                    emit(recipesRepository.getFavoriteRecipeIds())
                }
            ) { recipes, favoriteIds ->
                recipes to favoriteIds
            }.retry()
                .cancellable()
                .collect { (recipes: List<Recipe>, favoriteIds: List<String>) ->
                    val newState = _state.value.copy(
                        recipes = recipes,
                        isLoading = false,
                        error = false,
                        favoriteIds = favoriteIds
                    )
                    _state.value = newState
                }
        }
        jobs.add(job)
    }

    companion object {
        const val REFRESH_TIME: Long = 5 * 60 * 1000
    }
}
