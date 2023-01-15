package com.lordsam.virtualcloset.screens

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.camera.CameraView


@OptIn(ExperimentalGlideComposeApi::class)
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
            CameraView(onImageCaptured = { uri, fromGallery ->
                Log.d(ContentValues.TAG, "Image Uri Captured from Camera View")
                //Todo : use the uri as needed
                photoUri = uri
                showImage = true
            }, onError = { imageCaptureException ->
                Log.d(ContentValues.TAG, "Error")
            })
        } else {
            ImageManger(photoUri) {
                showImage = false
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageManger(photoUri: Uri, onClick: () -> Unit) {
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
        }
    }
}
