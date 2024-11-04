package com.example.arvrproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.arvrproject.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("AR VR Project") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    Box(contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Account",
                                Modifier.padding(top = 12.dp, bottom = 0.dp, end = 16.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                NavigationBarItem(
                    label = { Text("Home") },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == Screen.HomeScreen.route
                    } == true,
                    onClick = { navController.navigate(Screen.HomeScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Go to home"
                        )
                    }
                )
                NavigationBarItem(
                    label = { Text("Add Data") },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == Screen.HomeScreen.route
                    } == true,
                    onClick = { navController.navigate(Screen.HomeScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Data"
                        )
                    }
                )
                NavigationBarItem(
                    label = { Text("Settings") },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == Screen.HomeScreen.route
                    } == true,
                    onClick = { navController.navigate(Screen.HomeScreen.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = """
                        Welcome to our AR VR App! 
                        
                    """.trimIndent(),
            )
        }
    }
}
