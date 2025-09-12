package com.example.mybestnews.screen.navigation

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application.proto.Settings
import com.example.mybestnews.screen.Greeting
import com.example.mybestnews.screen.newsPreferences.UserNewsPreferencesScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import settingsDataStore

@Composable
fun MyBestNewsApp(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = Screens.GREETING_SCREEN.name
    ){
        composable(Screens.GREETING_SCREEN.name) {

            val dataStore = navController.context.settingsDataStore

            Greeting(
                onStartClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.data.collect {
                            if(it.newUser){
                                navController.navigate(Screens.NEWS_PREFERENCES_SCREEN.name)
                            }else{
                                navController.navigate(Screens.NEWS_SCREEN.name)
                            }
                        }
                    }
                }
            )
        }

        composable(Screens.NEWS_PREFERENCES_SCREEN.name) {
            UserNewsPreferencesScreen()
        }

        composable(Screens.NEWS_SCREEN.name) {

        }
    }
}