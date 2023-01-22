package com.lordsam.virtualcloset.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lordsam.virtualcloset.screens.CameraScreen
import com.lordsam.virtualcloset.screens.ClosetFormScreen
import com.lordsam.virtualcloset.screens.HomeScreen
import com.lordsam.virtualcloset.screens.SplashScreen
import com.lordsam.virtualcloset.viewmodel.ClosetViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(closetViewModel: ClosetViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Routes.splashScreen) {
        composable(Routes.splashScreen) {
            SplashScreen(navController = navController)
        }

        composable(Routes.homeScreen) {
            HomeScreen(navController = navController)
        }

        composable(Routes.cameraScreen) {
            CameraScreen(navController = navController)
        }

        composable(
            Routes.closetFormScreen + "/{photoUri}",
            arguments = listOf(navArgument(name = "photoUri"){type = NavType.StringType})
        ) {
            ClosetFormScreen(
                closetViewModel = closetViewModel,
                navController = navController,
                photoUri = it.arguments?.getString("photoUri")!!
            )
        }
    }
}

