package com.lordsam.virtualcloset.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.components.CategoryCard
import com.lordsam.virtualcloset.models.categoryList


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = stringResource(R.string.home),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = stringResource(R.string.search_icon),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_more_button)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Surface (
            modifier = Modifier
                .padding(4.dp)
                ) {
            LazyColumn{
                items(categoryList()){ item ->
                    CategoryCard(
                        imageID = item.imageID,
                        text = item.text
                    )
                }
            }
        }
    }
}