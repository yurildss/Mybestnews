package com.example.mybestnews.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybestnews.ui.theme.MyBestNewsTheme

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Welcome to the best news app",
            modifier = Modifier.padding(24.dp),
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace
        )
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.padding(24.dp).fillMaxWidth(0.6f).align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Start")
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    MyBestNewsTheme {
        Greeting()
    }
}