package com.lordsam.virtualcloset.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.components.ClosetCard
import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.viewmodel.ClosetViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClosetGridScreen(
    closetViewModel: ClosetViewModel,
    navController: NavController,
    category: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp
            ) {
                Text(
                    text = category,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    ) {

        var showImage by remember {
            mutableStateOf(false)
        }

        var passedUri by remember {
            mutableStateOf("")
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val list = closetViewModel.closetList.collectAsState().value
            val categorisedList = arrayListOf<ClosetData>()

            for (closet in list) {
                if (closet.category == category) {
                    categorisedList.add(closet)
                }
            }

            if (categorisedList.isNotEmpty()) {
                if (!showImage) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(128.dp),
                        content = {
                            items(categorisedList) { item ->
                                ClosetCard(
                                    closet = item,
                                    onLongClick = {
                                        val uri = it.uri
                                        closetViewModel.deleteCloset(it)
                                        uri.toUri().toFile().delete()
                                    },
                                    onDoubleClick = {
                                        passedUri = it
                                        showImage = true
                                    }
                                )
                            }
                        }
                    )
                } else {
                    GlideImage(
                        model = passedUri.toUri(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                showImage = false
                            }
                    )
                }
            }else{
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "No items!")
                }
            }
        }
    }
}