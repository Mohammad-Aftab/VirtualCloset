package com.lordsam.virtualcloset.camera

import android.annotation.SuppressLint
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.lordsam.virtualcloset.R


@Composable
fun CameraView(navController: NavController, onImageCaptured: (Uri, Boolean) -> Unit, onError: (ImageCaptureException) -> Unit) {

    val context = LocalContext.current
    val lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val imageCapture: ImageCapture = remember {
        ImageCapture.Builder().build()
    }
    CameraPreviewView(
        navController,
        imageCapture,
        lensFacing
    ) { cameraUIAction ->
        when (cameraUIAction) {
            is CameraUIAction.OnCameraClick -> {
                imageCapture.takePicture(context, lensFacing, onImageCaptured, onError)
            }
        }
    }
}


@SuppressLint("RestrictedApi")
@Composable
private fun CameraPreviewView(
    navController: NavController,
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    cameraUIAction: (CameraUIAction) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    val previewView = remember { PreviewView(context) }
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize()) {

        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom
        ) {
            CameraControls(navController, cameraUIAction)
        }

    }
}

@Composable
fun CameraControls(navController: NavController, cameraUIAction: (CameraUIAction) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clickable {
            navController.popBackStack()
        })

        CameraControl(
            Icons.Rounded.AddCircle,
            R.string.camera_shutter,
            modifier= Modifier
                .size(64.dp)
                .padding(1.dp)
                .border(1.dp, Color.White, CircleShape),
            onClick = { cameraUIAction(CameraUIAction.OnCameraClick) }
        )

        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clickable {
            navController.popBackStack()
        })
    }
}


@Composable
fun CameraControl(
    imageVector: ImageVector,
    contentDescId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector,
            contentDescription = stringResource(id = contentDescId),
            modifier = modifier,
            tint = Color.White
        )
    }

}