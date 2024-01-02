package com.example.todolistcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistcompose.data.viewmodel.MainViewModel
import com.example.todolistcompose.ui.theme.favorite_screen.FavoriteScreen
import com.example.todolistcompose.ui.theme.home_screen.HomeScreen
import com.example.todolistcompose.ui.theme.update_screen.UpdateScreen

@Composable
fun AppNavigation(mainViewModel: MainViewModel,navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(mainViewModel = mainViewModel, onUpdate = {
                navController.navigate(route = "${Screen.UpdateScreen.route}/$it")
            })
        }
        composable(
            route = "${Screen.UpdateScreen.route}/{id}",
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
        composable(route = Screen.FavoriteScreen.route){
            FavoriteScreen(navController = navController, mainViewModel = mainViewModel, onUpdate = {
                navController.navigate(route = "${Screen.UpdateScreen.route}/$it")
            })
        }
    }
}