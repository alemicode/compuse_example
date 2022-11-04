package com.mohamad.myapplication.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mohamad.myapplication.R
import com.mohamad.myapplication.domain.model.Recipe
import com.mohamad.myapplication.presentation.ui.recipe_list.FoodCategory
import com.mohamad.myapplication.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                10.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {

        Column() {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = R.drawable.dog).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(), contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                    )

                }
            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                    val rank = recipe.rating.toString()
                    Text(
                        text = rank,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}


@Composable
fun TitleFoods(
    food: FoodCategory,
    onClick: () -> Unit
) {
    Card(

        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(border = BorderStroke(1.dp, color = colorResource(id = R.color.black)))
            .background(color = Color.Blue),
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            style = TextStyle(color = Color.Black),
            text = food.name
        )
    }
}