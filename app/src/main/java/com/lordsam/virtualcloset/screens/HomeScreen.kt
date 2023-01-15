package com.lordsam.virtualcloset.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.camera.CameraView
import com.lordsam.virtualcloset.components.CategoryCard
import com.lordsam.virtualcloset.models.categoryList
import com.lordsam.virtualcloset.navigation.Routes


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    //Camera Permission
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    CameraPermission(permissionState = permissionState)

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
                    if (permissionState.hasPermission){
                        navController.navigate(Routes.cameraScreen)
                    }else {
                        permissionState.launchPermissionRequest()
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
fun CameraPermission(
    permissionState: PermissionState,
) {
    val context = LocalContext.current

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            /*..*/
        },
        permissionNotAvailableContent = {
            Toast.makeText(context, "Permission Not available! Please Check the permission settings.", Toast.LENGTH_SHORT).show()
        }
    ) {
        Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
    }
}