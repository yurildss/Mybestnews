package com.example.mybestnews.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybestnews.screen.Greeting
import com.example.mybestnews.screen.newsPreferences.UserNewsPreferencesScreen

@Composable
fun MyBestNewsApp(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = Screens.GREETING_SCREEN.name
    ){
        composable(Screens.GREETING_SCREEN.name) {
            Greeting(
                navController = navController
            )
        }

        composable(Screens.NEWS_PREFERENCES_SCREEN.name) {
            UserNewsPreferencesScreen(
                onSuccessSave = {
                    navController.navigate(Screens.NEWS_SCREEN.name)
                }
            )
        }

        composable(Screens.NEWS_SCREEN.name) {

        }
    }
}