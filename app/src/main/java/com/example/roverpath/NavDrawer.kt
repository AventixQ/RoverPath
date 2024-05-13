package com.example.roverpath

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

sealed class Screens(
    val title: String,
    val route: String,
) {
    object Main: Screens(
        title = "Ekran główny",
        route = "main",
    )

    object All: Screens(
        title = "Wszystkie trasy",
        route = "all",
    )

    object Easy: Screens(
        title = "Łatwe trasy",
        route = "easy",
    )
    object Normal: Screens(
        title = "Normalne trasy",
        route = "normal",
    )
    object Hard: Screens(
        title = "Trudne trasy",
        route = "hard",
    )

    object LessThanTen: Screens(
        title = "Trasy poniżej 10 km",
        route = "lessthanten",
    )

    object MoreThanTen: Screens(
        title = "Trasy powyżej 10 km",
        route = "morethanten",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(themeViewModel: ThemeViewModel, navController: NavHostController, drawerState: DrawerState) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screens.Main.route
    val coroutineScope = rememberCoroutineScope()
    val screens = listOf(
        Screens.Main,
        Screens.All,
        Screens.Easy,
        Screens.Normal,
        Screens.Hard,
        Screens.LessThanTen,
        Screens.MoreThanTen
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Divider(thickness = 1.dp, modifier = Modifier.padding(bottom = 20.dp))

                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "RoverPath") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu icon")
                        }

                    },
                    actions = {

                        IconButton(onClick = { navController.navigate("main") }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(onClick = {
                            themeViewModel.toggleTheme()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.WbSunny,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                NavHost(navController, startDestination = "main") {

                    composable("main") {
                        WelcomeScreen()
                    }
                    composable("all") {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            MainTabletScreen(navController)
                        } else {
                            MainScreen(navController)
                        }
                    }
                    composable("details/{trailId}") { backStackEntry ->
                        DetailsScreen(backStackEntry.arguments?.getString("trailId"),navController)
                    }
                    composable(Screens.Easy.route) {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            DifficultyTabletScreen(navController, DifficultyLevel.EASY)
                        } else {
                            DifficultyScreen(navController, DifficultyLevel.EASY)
                        }
                    }
                    composable(Screens.Normal.route) {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            DifficultyTabletScreen(navController, DifficultyLevel.EASY)
                        } else {
                            DifficultyScreen(navController, DifficultyLevel.NORMAL)
                        }
                    }
                    composable(Screens.Hard.route) {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            DifficultyTabletScreen(navController, DifficultyLevel.EASY)
                        } else {
                            DifficultyScreen(navController, DifficultyLevel.HARD)
                        }
                    }
                    composable(Screens.LessThanTen.route) {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            KmLessTabletScreen(navController)
                        } else {
                            KmLessScreen(navController)
                        }
                    }
                    composable(Screens.MoreThanTen.route) {
                        val configuration = LocalConfiguration.current
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            KmMoreTabletScreen(navController)
                        } else {
                            KmMoreScreen(navController)
                        }
                    }
                }
            }
        }
    }
}


