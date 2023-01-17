package com.lordsam.virtualcloset.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lordsam.virtualcloset.screens.CameraScreen
import com.lordsam.virtualcloset.screens.ClosetFormScreen
import com.lordsam.virtualcloset.screens.HomeScreen
import com.lordsam.virtualcloset.screens.SplashScreen


@Composable
fun Navigation() {
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

        composable(Routes.closetFormScreen) {
            ClosetFormScreen(navController = navController)
        }
    }
}