package com.lordsam.virtualcloset.models

import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.data.CategoryData


fun categoryList() = listOf(
    CategoryData(
        imageID = R.drawable.accessories,
        text = "Accessories"
    ),
    CategoryData(
        imageID = R.drawable.bottom,
        text = "Bottoms"
    ),
    CategoryData(
        imageID = R.drawable.shoe,
        text = "Footwear"
    ),
    CategoryData(
        imageID = R.drawable.jewelery,
        text = "Jewelery"
    ),
    CategoryData(
        imageID = R.drawable.others,
        text = "Others"
    ),
    CategoryData(
        imageID = R.drawable.saree,
        text = "Saree"
    ),
    CategoryData(
        imageID = R.drawable.suit,
        text = "Suits"
    ),
    CategoryData(
        imageID = R.drawable.top,
        text = "Tops"
    ),
    CategoryData(
        imageID = R.drawable.bikini,
        text = "Under-Garments"
    ),
)