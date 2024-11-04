package com.example.arvrproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arvrproject.screens.HomeScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}
