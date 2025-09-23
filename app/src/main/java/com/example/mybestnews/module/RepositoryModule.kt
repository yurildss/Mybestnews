package com.example.mybestnews.module

import com.example.mybestnews.repository.NewsRepository
import com.example.mybestnews.repository.OfflineNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(
        offlineNewsRepository: OfflineNewsRepository
    ): NewsRepository
}

