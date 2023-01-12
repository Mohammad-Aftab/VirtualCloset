package com.lordsam.virtualcloset.models

import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.data.CategoryData


fun categoryList() = listOf(
    CategoryData(
        imageID = R.drawable.top,
        text = "Tops"
    ),
    CategoryData(
        imageID = R.drawable.bottom,
        text = "Bottoms"
    ),
    CategoryData(
        imageID = R.drawable.suit,
        text = "Suits"
    ),
    CategoryData(
        imageID = R.drawable.saree,
        text = "Saree"
    ),
    CategoryData(
        imageID = R.drawable.shoe,
        text = "Shoes"
    ),
)