package com.example.todolistcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistcompose.data.viewmodel.MainViewModel
import com.example.todolistcompose.ui.theme.home_screen.HomeScreen
import com.example.todolistcompose.ui.theme.update_screen.UpdateScreen

@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.name) {
        composable(route = Screen.HomeScreen.name) {
            HomeScreen(mainViewModel = mainViewModel, onUpdate = {
                navController.navigate(route = "${Screen.UpdateScreen.name}/$it")
            })
        }
        composable(
            route = "${Screen.UpdateScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            }),

            ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                UpdateScreen(
                    id = id!!,
                    mainViewModel = mainViewModel,
                    onBack = { navController.popBackStack() })
            }
        }
    }
}