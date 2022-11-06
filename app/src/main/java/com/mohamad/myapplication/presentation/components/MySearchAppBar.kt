package com.mohamad.myapplication.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mohamad.myapplication.R
import com.mohamad.myapplication.presentation.ui.recipe_list.FoodCategory

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    scrollPosition: Int,
    onChangeScrollPosition: (Int) -> Unit,
    onToggleTheme: () -> Unit,

    ) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    },
                )
                Image(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onToggleTheme()
                        },
                    painter = painterResource(
                        id = R.drawable.more
                    ),
                    contentDescription = "",
                )
            }
            val scrollState = rememberScrollState()

            LazyRow(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp),

                ) {
                itemsIndexed(items = categories) { index, item ->
                    FoodCategoryChip(
                        category = item.value,
                        isSelected = selectedCategory == item,
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                        onSelectedCategoryChanged = {
                            onChangeScrollPosition(scrollState.value)
                            onSelectedCategoryChanged(it)
                        }
                    )

                }
            }


        }


    }
}














