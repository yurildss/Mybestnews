package com.example.mybestnews.screen.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mybestnews.model.Article
import com.example.mybestnews.screen.Greeting
import com.example.mybestnews.screen.feedScreen.FeedScreen
import com.example.mybestnews.screen.news.NewsScreen
import com.example.mybestnews.screen.newsPreferences.UserNewsPreferencesScreen
import com.google.gson.Gson

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
            FeedScreen(
                onNewsClick = {
                    val json = Uri.encode(Gson().toJson(it))
                    navController.navigate("${Screens.VISUALIZE_NEWS_SCREEN.name}/$json")
                }
            )
        }

        composable(route = "${Screens.VISUALIZE_NEWS_SCREEN.name}/{article}",
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("article")
            val article = Gson().fromJson(json, Article::class.java)
            NewsScreen(article = article)
        }
    }
}