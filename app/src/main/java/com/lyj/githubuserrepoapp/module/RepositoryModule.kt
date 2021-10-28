package com.lyj.githubuserrepoapp.module

import com.lyj.githubuserrepoapp.data.repoisotry.GithubApiRepositoryImpl
import com.lyj.githubuserrepoapp.data.source.api.service.GithubApiService
import com.lyj.githubuserrepoapp.domain.repository.GithubApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGithubApiRepository(
        githubApiService: GithubApiService
    ): GithubApiRepository = GithubApiRepositoryImpl(githubApiService)
}