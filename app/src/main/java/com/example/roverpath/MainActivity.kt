package com.example.roverpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()
            App(isDarkThemeEnabled, themeViewModel)
        }
    }
}

@Composable
fun App(isDarkThemeEnabled: Boolean, themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    //val themeViewModel: ThemeViewModel = viewModel()
    ReplyTheme(darkTheme = isDarkThemeEnabled){
        NavDrawer(themeViewModel = themeViewModel, navController = navController, drawerState = drawerState)
    }

}

