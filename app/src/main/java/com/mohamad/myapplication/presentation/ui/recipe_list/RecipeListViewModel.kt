package com.mohamad.myapplication.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamad.myapplication.domain.model.Recipe
import com.codingwithmitch.mvvmrecipeapp.repository.RecipeRepository
import com.mohamad.myapplication.presentation.ui.recipe_list.FoodCategory
import com.mohamad.myapplication.presentation.ui.recipe_list.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel

class RecipeListViewModel
@Inject
constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition: Int = 0

    init {
        newSearch()
    }

    fun newSearch() {
//        viewModelScope.launch {
//            val result = repository.search(
//                token = token,
//                page = 1,
//                query = query.value
//            )
//            recipes.value = result
//        }
        recipes.value = generateFakeData(query.value)
    }


    fun generateFakeData(query: String): List<Recipe> {
        var recipe1 = Recipe(
            id = 1,
            "this is ${query}",
            featuredImage = "https://picsum.photos/id/237/200/300",
            description = "I love this food",
            rating = 10
        )
        return listOf(
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
            recipe1,
        )
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        categoryScrollPosition = position
    }
}
















