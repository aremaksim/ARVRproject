package com.example.arvrproject.navigation

sealed class Screen (val route: String){

    object HomeScreen : Screen("home")

}