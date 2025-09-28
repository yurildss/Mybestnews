package com.example.mybestnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.mybestnews.screen.Greeting
import com.example.mybestnews.screen.navigation.MyBestNewsApp
import com.example.mybestnews.ui.theme.MyBestNewsTheme
import com.example.mybestnews.work.DownloadWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val downLoadRequest: WorkRequest = PeriodicWorkRequestBuilder<DownloadWorker>(12, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(this).enqueue(downLoadRequest)

        enableEdgeToEdge()
        setContent {
            MyBestNewsTheme {
                MyBestNewsApp()
            }
        }
    }
}
