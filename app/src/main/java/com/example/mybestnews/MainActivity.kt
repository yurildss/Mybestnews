package com.example.mybestnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mybestnews.screen.navigation.MyBestNewsApp
import com.example.mybestnews.ui.theme.MyBestNewsTheme
import com.example.mybestnews.work.DownloadWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val downLoadRequest = PeriodicWorkRequestBuilder<DownloadWorker>(12, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DownloadWork",
            ExistingPeriodicWorkPolicy.KEEP,
            downLoadRequest
        )


        enableEdgeToEdge()
        setContent {
            MyBestNewsTheme {
                MyBestNewsApp()
            }
        }
    }
}
