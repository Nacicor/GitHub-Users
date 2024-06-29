package com.example.github_users._di

import com.example.github_users.data.GitHubRetrofitInstance
import com.example.github_users.data.GitHubService
import com.example.github_users.data.UserRepository
import com.example.github_users.data.UserRepositoryImpl
import com.example.github_users.domain.usecase.GetUserDetailDataUseCase
import com.example.github_users.domain.usecase.GetUserPagingDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGitHubService(): GitHubService {
        return GitHubRetrofitInstance.api
    }

    @Provides
    fun provideUserRepository(service: GitHubService): UserRepository {
        return UserRepositoryImpl(service)
    }

    @Provides
    fun provideGetUserPagingDataUseCase(repository: UserRepository): GetUserPagingDataUseCase {
        return GetUserPagingDataUseCase(repository)
    }

    @Provides
    fun provideGetUserDetailDataUseCase(repository: UserRepository): GetUserDetailDataUseCase {
        return GetUserDetailDataUseCase(repository)
    }
}