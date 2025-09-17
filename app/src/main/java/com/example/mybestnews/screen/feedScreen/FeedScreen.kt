package com.example.mybestnews.screen.feedScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.mybestnews.model.News
import com.example.mybestnews.ui.theme.MyBestNewsTheme

@Composable
fun FeedScreen(
    viewModel: FeedScreenViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsState()

    NewsFeed(newsList = uiState.value.news)
}

@Composable
fun NewsFeed(
    modifier: Modifier = Modifier,
    newsList: List<News>){
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(newsList) { news ->
            NewsCard(news = news)
        }
    }
}

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news: News
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = news.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = news.publishedAt, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
            }
            Text(text = news.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                overflow = TextOverflow.Ellipsis,)
        }
    }
}

@Composable
@Preview
fun NewsCardPreview(){
    MyBestNewsTheme {
        Surface(Modifier.fillMaxSize()) {
            Column {
                NewsCard(
                    news = News(
                        title = "",
                        description = "",
                        url = "",
                        publishedAt = "",
                        author = TODO(),
                        urlToImage = TODO(),
                        content = TODO()
                    )
                )
            }
        }
    }
}