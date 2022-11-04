package com.mohamad.myapplication.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamad.myapplication.domain.model.Recipe
import com.mohamad.myapplication.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    val repository: RecipeRepository,
    @Named("auth_token") val token: String,
) : ViewModel() {

    var scrollPosition = 0
    val query = mutableStateOf("")
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    init {
        newSearch("chiken")
    }

    fun newSearch(query: String) {
        Log.d("TAG", "newSearch: kjafdj ${token}")
        viewModelScope.launch {
//            val result = repository.search(
//                token = token,
//                page = 1,
//                query = "chicken"
//            )


            recipes.value = getFakeData(query)
        }
    }

    fun getRepo() = repository

    fun getAuthToken() = token

    fun setPosition(scrollPosition: Int) {
        this.scrollPosition = scrollPosition
    }

    fun getFakeData(query: String): MutableList<Recipe> {
        val data = mutableListOf<Recipe>()
        var file1 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",
            id = 1,
            title = "${query} this is Pizza",
            publisher = "1",
            rating = 100,
            description = "this pizza is a very good pizza"
        )
        var file2 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 2,
            title = "${query} this is Pizza",
            publisher = "2",
            rating = 100,
            description = "this pizza is a very good hambergur"
        )
        var file3 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 3,
            title = "${query} this is Pizza",
            publisher = "3",
            rating = 100,
            description = "this pizza is a very good lazania"
        )
        var file4 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 4,
            title = "${query} this is Pizza",
            publisher = "4",
            rating = 100,
            description = "this pizza is a very good hot"
        )
        var file5 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 5,
            title = "${query} this is Pizza",
            publisher = "5",
            rating = 100,
            description = "this pizza is a very good pizza"
        )
        var file6 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 6,
            title = "${query} this is Pizza",
            publisher = "$6",
            rating = 100,
            description = "this pizza is a very good sandwich"
        )
        var file7 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 7,
            title = "${query} this is Pizza",
            publisher = "7",
            rating = 100,
            description = "this pizza is a very good peperony"
        )
        var file8 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 1,
            title = "${query} this is Pizza",
            publisher = "8",
            rating = 100,
            description = "this pizza is a very good candy"
        )

        var file9 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 1,
            title = "${query} this is Pizza",
            publisher = "8",
            rating = 100,
            description = "this pizza is a very good candy"
        )
        var file10 = Recipe(
            featuredImage = "https://picsum.photos/200/300.jpg",

            id = 1,
            title = "this is candy",
            publisher = "8",
            rating = 100,
            description = "this pizza is a very good candy"
        )

        data.add(file1)
        data.add(file2)
        data.add(file3)
        data.add(file4)
        data.add(file5)
        data.add(file6)
        data.add(file7)
        data.add(file8)
        data.add(file9)
        data.add(file10)
        data.add(file8)
        data.add(file8)
        data.add(file8)
        data.add(file8)
        data.add(file8)
        data.add(file8)
        data.add(file8)

        return data
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun newSearch() {
        newSearch(query.value)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }
}
















