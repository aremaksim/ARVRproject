package com.example.arvrproject.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.arvrproject.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Camera launcher to take a picture
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            imageBitmap = bitmap
        }
    }

    // Permission launcher to request camera permission
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch() // Launch the camera if permission is granted
        } else {
            Toast.makeText(context, "Camera permission is required to take pictures", Toast.LENGTH_SHORT).show()
        }
    }

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
            Button(
                onClick = { checkCameraPermission(context, permissionLauncher) }, // Check permission before launching camera
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Open Camera")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display the captured image if available
            imageBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}

// Function to check for camera permission
private fun checkCameraPermission(context: Context, permissionLauncher: ActivityResultLauncher<String>) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        permissionLauncher.launch(Manifest.permission.CAMERA) // Launch the permission request
    } else {
        permissionLauncher.launch(Manifest.permission.CAMERA) // Request the permission
    }
}
