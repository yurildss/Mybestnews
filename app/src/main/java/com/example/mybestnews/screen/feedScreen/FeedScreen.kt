package com.example.mybestnews.screen.feedScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybestnews.ui.theme.MyBestNewsTheme

@Composable
fun FeedScreen(){

}

@Composable
fun NewsCard(modifier: Modifier = Modifier){
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
                Text(text = "Bleacher Report", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Sports", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
            }
            Text(text = "Sports journalists and bloggers covering " +
                    "NFL, MLB, NBA, NHL, MMA, college football and basketball," +
                    " NASCAR, fantasy sports and more. News, photos, mock drafts," +
                    " game scores, player profiles and more!",
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
            NewsCard()

        }
    }
}