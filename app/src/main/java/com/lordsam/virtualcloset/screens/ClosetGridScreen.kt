package com.lordsam.virtualcloset.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lordsam.virtualcloset.components.ClosetCard
import com.lordsam.virtualcloset.viewmodel.ClosetViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClosetGridScreen(
    closetViewModel: ClosetViewModel,
    navController: NavController,
    category: String
){
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(text = category)
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val list = closetViewModel.closetList.collectAsState().value
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                content = {
                    items(list){item ->
                        ClosetCard(closet = item)
                    }
                }
            )
        }
    }
}