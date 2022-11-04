package com.mohamad.myapplication.network.response

import com.mohamad.myapplication.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
        var count: Int,

    @SerializedName("results")
        var recipes: List<RecipeDto>,
)