package com.example.mybestnews.screen.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mybestnews.model.Article

@Composable
fun NewsScreen(article: Article, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Text(text = article.title)
            Text(text = article.body!!)
        }
    }
}