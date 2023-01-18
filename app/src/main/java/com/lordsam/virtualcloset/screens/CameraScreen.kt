package com.lordsam.virtualcloset.screens

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.R
import com.lordsam.virtualcloset.camera.CameraView
import com.lordsam.virtualcloset.navigation.Routes


@Composable
fun CameraScreen(navController: NavController) {

    var photoUri: Uri = Uri.EMPTY
    var showImage by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (!showImage) {
            CameraView(
                onImageCaptured = { uri, _ ->
                    Log.d(ContentValues.TAG, "Image Uri Captured from Camera View")
                    //Todo : use the uri as needed
                    photoUri = uri
                    showImage = true
                }) {
                Log.d(ContentValues.TAG, "Error")
            }
        } else {
            ImageManger(navController, photoUri)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageManger(navController: NavController, photoUri: Uri) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GlideImage(
                model = photoUri,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_clear_24),
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            //Delete photo
                            photoUri.toFile().delete()
                            navController.popBackStack()
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            //Send Back to Form Screen with Uri
                        }
                )
            }
        }
    }
}
