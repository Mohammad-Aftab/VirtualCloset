package com.lordsam.virtualcloset.screens

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.components.CategoryCard
import com.lordsam.virtualcloset.models.categoryList
import com.lordsam.virtualcloset.navigation.Routes
import com.lordsam.virtualcloset.viewmodel.ClosetViewModel


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    //Camera Permission
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )
    ManagePermission(permissionState = permissionState)

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
                onClick = {
                    if (permissionState.allPermissionsGranted){
                        navController.navigate(Routes.cameraScreen)
                    }else {
                        permissionState.launchMultiplePermissionRequest()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_photo_camera_24),
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ManagePermission(
    permissionState: MultiplePermissionsState,
) {
    val context = LocalContext.current

    PermissionRequired(
        permissionState = permissionState.permissions[0],
        permissionNotGrantedContent = {
            /*..*/
        },
        permissionNotAvailableContent = {
            Toast.makeText(context, "Permission Not available! Please Check the permission settings.", Toast.LENGTH_SHORT).show()
        }
    ) {
        /*..*/
    }
}