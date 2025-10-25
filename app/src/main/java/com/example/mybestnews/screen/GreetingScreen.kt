package com.example.mybestnews.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mybestnews.R
import com.example.mybestnews.screen.navigation.Screens
import com.example.mybestnews.ui.theme.MyBestNewsTheme

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UserFlowViewModel = hiltViewModel()
    ) {

    val uiState = viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "My Best News",
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 36.sp,
            fontFamily = FontFamily.Monospace
        )
        Text(
            "Welcome to the best news app",
            modifier = Modifier.padding(top = 10.dp, start = 24.dp),
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace
        )
        Image(
            painter = painterResource(id = R.drawable.newsapp),
            contentDescription = "logo"
        )
        Button(
            onClick = {
                if(uiState.value.newUser){
                    navController.navigate(Screens.NEWS_SCREEN.name)
                }else{
                    navController.navigate(Screens.NEWS_PREFERENCES_SCREEN.name)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(0.75f)
                .fillMaxHeight(0.15f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Start",
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    MyBestNewsTheme {

    }
}