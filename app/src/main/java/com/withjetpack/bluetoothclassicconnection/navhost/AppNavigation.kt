package com.withjetpack.bluetoothclassicconnection.navhost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.withjetpack.bluetoothclassicconnection.presentation.DetailScreen
import com.withjetpack.bluetoothclassicconnection.presentation.main.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("detailScreen") {
            DetailScreen(navController)
        }
    }
}


