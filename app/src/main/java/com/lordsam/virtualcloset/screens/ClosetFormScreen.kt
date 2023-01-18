package com.lordsam.virtualcloset.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.models.getCategories
import com.lordsam.virtualcloset.navigation.Routes


@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClosetFormScreen(navController: NavHostController) {

    var expandedDropdown by remember {
        mutableStateOf(false)
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }
    var closetName by remember {
        mutableStateOf("")
    }
    var closetDescription by remember {
        mutableStateOf("")
    }
    var closetLocation by remember {
        mutableStateOf("")
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expandedDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp
            ) {
                Text(
                    text = "Closet Form",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) {

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Capture Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clickable {
                            navController.navigate(Routes.cameraScreen)
                        }
                )

                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = { selectedCategory = it },
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Category") },
                    trailingIcon = {
                        Icon(icon, "Drop Down Icon",
                            Modifier.clickable { expandedDropdown = !expandedDropdown })
                    }
                )

                DropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    getCategories().forEach {
                        DropdownMenuItem(onClick = {
                            expandedDropdown = false
                            selectedCategory = it
                        }) {
                            Text(text = it)
                        }
                    }
                }

                OutlinedTextField(
                    value = closetName,
                    onValueChange = { closetName = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = closetDescription,
                    onValueChange = { closetDescription = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = closetLocation,
                    onValueChange = { closetLocation = it },
                    label = { Text(text = "Kept At") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    ) {
                        Text(text = "Save")
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}