package com.mohamad.myapplication.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohamad.myapplication.presentation.theme.AppTheme
import com.mohamad.myapplication.presentation.BaseApplication
import com.mohamad.myapplication.presentation.components.RecipeCard
import com.mohamad.myapplication.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment: Fragment() {


    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(
                    darkTheme = application.isDark.value
                ) {

                    val recipes = viewModel.recipes.value

                    val query = viewModel.query.value

                    val selectedCategory = viewModel.selectedCategory.value

                    val categoryScrollPosition = viewModel.categoryScrollPosition

//                    val loading = viewModel.loading.value

                    Column(
                        modifier = Modifier.background(color = MaterialTheme.colors.surface)
                    ) {

                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            categories = getAllFoodCategories(),
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            scrollPosition = categoryScrollPosition,
                            onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                            onToggleTheme = {
                                application.toggleLightTheme()
                            }
                        )

                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe = recipe, onClick = {})
                            }
                        }

                    }
                }


            }
        }
    }

}























