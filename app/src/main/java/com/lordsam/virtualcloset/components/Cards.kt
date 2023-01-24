package com.lordsam.virtualcloset.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.data.ClosetData
import java.net.URI


@Composable
fun CategoryCard(
    imageID: Int,
    text: String,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick()
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(80.dp)
            ) {

                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = stringResource(R.string.category_icon),
                    modifier = Modifier
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.h6,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun ClosetCard(
    closet: ClosetData,
    onLongClick: (ClosetData) -> Unit,
    onDoubleClick: (String) -> Unit
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var modifier by remember {
        mutableStateOf(
            Modifier
                .height(128.dp)
                .width(100.dp)
                .padding(4.dp)
        )
    }

    val tempDate = closet.dateTime.toString().split(" ")
    val date = tempDate[1] + " " + tempDate[2] + " " + tempDate[3]

    Card(
        modifier = modifier
            .combinedClickable(
                onClick = {
                    expanded = !expanded
                    modifier = if (expanded) {
                        Modifier
                            .height(256.dp)
                            .fillMaxWidth()
                            .padding(4.dp)
                    } else {
                        Modifier
                            .height(128.dp)
                            .width(100.dp)
                            .padding(4.dp)
                    }
                },
                onLongClick = {
                    showDialog = true
                },
                onDoubleClick = {
                    onDoubleClick(closet.uri)
                }
            ),
        shape = RoundedCornerShape(CornerSize(8.dp)),
        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            GlideImage(
                model = closet.uri.toUri(),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = closet.name,
                modifier = Modifier
                    .padding(4.dp)
            )
            if (expanded) {
                Text(
                    text = "Description: " + closet.description,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = "Kept At: " + closet.location,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = date,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }

        if (showDialog) {

            Dialog(onDismissRequest = { /*TODO*/ }) {
                Surface(
                    shape = RoundedCornerShape(CornerSize(8.dp)),
                    elevation = 8.dp,
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(text = "Delete the file?")

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(4.dp)
                        ) {

                            Button(onClick = {
                                onLongClick(closet)
                                showDialog = false
                            },
                                modifier = Modifier.padding(4.dp)) {
                                Text(text = "Delete")
                            }

                            Button(onClick = {
                                showDialog = false
                            },
                                modifier = Modifier.padding(4.dp)) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowDialog(onDelete: () -> Unit) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            shape = RoundedCornerShape(CornerSize(8.dp)),
            elevation = 4.dp,
            modifier = Modifier
                .padding(4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(text = "Delete the file?")
                Button(onClick = {
                    onDelete()
                }) {
                    Text(text = "Delete")
                }
            }
        }
    }
}
