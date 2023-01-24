package com.lordsam.virtualcloset.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.models.getCategories
import com.lordsam.virtualcloset.navigation.Routes
import com.lordsam.virtualcloset.viewmodel.ClosetViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClosetFormScreen(
    closetViewModel: ClosetViewModel,
    navController: NavHostController,
    photoUri: String
) {

    val ctx = LocalContext.current

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
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(4.dp)
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

                if (photoUri.isEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Capture Image",
                        modifier = Modifier
                            .size(120.dp)
                    )
                } else {
                    GlideImage(
                        model = Uri.decode(photoUri),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                    )
                }

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
                        onClick = {
                            //Save data

                            if (selectedCategory.isEmpty()){
                                Toast.makeText(ctx, "Please, select a category!", Toast.LENGTH_SHORT).show()
                            }else if (closetName.isEmpty()){
                                Toast.makeText(ctx, "Please, enter a name!", Toast.LENGTH_SHORT).show()
                            }else {
                                if (closetDescription.isEmpty()){
                                    closetDescription = "None"
                                }
                                if (closetLocation.isEmpty()){
                                    closetLocation = "None"
                                }
                                
                                val closet = ClosetData(
                                    name = closetName,
                                    category = selectedCategory,
                                    description = closetDescription,
                                    location = closetLocation,
                                    uri = photoUri
                                )

                                closetViewModel.addCloset(closet)
                                navController.navigate(Routes.homeScreen)
                            }
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    ) {
                        Text(text = "Save")
                    }

                    Button(
                        onClick = {
                            //Delete photo
                            photoUri.toUri().toFile().delete()
                            navController.navigate(Routes.homeScreen)
                        },
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