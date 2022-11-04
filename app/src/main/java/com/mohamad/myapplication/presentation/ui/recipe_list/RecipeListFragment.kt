package com.mohamad.myapplication.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import com.mohamad.myapplication.R

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingwithmitch.mvvmrecipeapp.presentation.components.FoodCategoryChip
import com.mohamad.myapplication.presentation.components.RecipeCard
import com.mohamad.myapplication.presentation.components.TitleFoods
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                val keyboardController = LocalSoftwareKeyboardController.current
                val selectedCategory = viewModel.selectedCategory.value




                Column {
                    val query = viewModel.query.value

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 8.dp
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(8.dp)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                value = query,
                                onValueChange = { newValue ->
                                    viewModel.onQueryChanged(newValue)
                                },
                                label = {
                                    Text(text = "Search")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Done,

                                    ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        viewModel.newSearch(query)

                                        keyboardController?.hide()
                                    }
                                ),
                                leadingIcon = {
                                    Icon(
                                        modifier = Modifier
                                            .width(20.dp)
                                            .height(20.dp),
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = "kf;sdj"
                                    )
                                },

                                )

                        }
                    }

                    Spacer(modifier = Modifier.padding(10.dp))
                    val context = LocalContext.current
                    val foods = getAllFoodCategories()
                    val scrollState = rememberScrollState()
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)
                    ) {
                        itemsIndexed(items = foods) { index, category ->
                            FoodCategoryChip(
                                category = category.value,
                                isSelected = selectedCategory == category,
                                onSelectedCategoryChanged = {
                                    viewModel.onSelectedCategoryChanged(it)
                                },
                                onExecuteSearch = viewModel::newSearch,
                            )
                        }
                    }


                    var recipes = viewModel.recipes.value
                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {
                                viewModel.newSearch(query)
                                keyboardController?.hide()

                                Toast.makeText(
                                    requireContext(),
                                    "${recipe.title}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                        }
                    }


                }


            }
        }
    }

}


















