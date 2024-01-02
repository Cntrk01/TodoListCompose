package com.example.todolistcompose.navigation

import com.example.todolistcompose.R

sealed class Screen(val route:String) {
    object HomeScreen : Screen("Home")
    object UpdateScreen : Screen("Update")
    object FavoriteScreen : Screen("Favorite")
}

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:Int
){
    object HomeScreen : BottomBarScreen(
        route = Screen.HomeScreen.route,
        title = "Home",
        icon = R.drawable.baseline_home_24
    )
    object FavoriteScreen : BottomBarScreen(
        route = Screen.FavoriteScreen.route,
        title = "Favorite",
        icon = R.drawable.baseline_favorite_24
    )
}