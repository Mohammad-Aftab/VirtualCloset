package com.lordsam.virtualcloset.screens

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lordsam.virtualcloset.camera.CameraView


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
                navController,
                onImageCaptured = { uri, fromGallery ->
                    Log.d(ContentValues.TAG, "Image Uri Captured from Camera View")
                    //Todo : use the uri as needed
                    photoUri = uri
                    showImage = true
                }, onError = { imageCaptureException ->
                    Log.d(ContentValues.TAG, "Error")
                })
        } else {
            ImageManger(photoUri)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageManger(photoUri: Uri) {
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
