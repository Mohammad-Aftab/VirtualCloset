package com.lordsam.virtualcloset.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lordsam.virtualcloset.camera.CameraView


@Composable
fun CameraScreen(navController: NavController){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraView(onImageCaptured = { uri, fromGallery ->
            Log.d(ContentValues.TAG, "Image Uri Captured from Camera View")
            //Todo : use the uri as needed

        }, onError = { imageCaptureException ->
            Log.d(ContentValues.TAG, "Error")
        })
    }
}