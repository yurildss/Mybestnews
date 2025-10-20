package com.example.mybestnews.screen.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybestnews.model.Article

@Composable
fun NewsScreen(article: Article, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.fillMaxSize().padding(
        top = 30.dp,
        end = 15.dp,
        bottom = 15.dp,
        start = 15.dp)
    ) {
        item {
            Text(
                text = article.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = article.body!!)
        }
    }
}