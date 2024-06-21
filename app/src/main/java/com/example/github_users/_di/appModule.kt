package com.example.github_users._di

import com.example.github_users.data.GitHubRetrofitInstance
import com.example.github_users.data.GitHubService
import com.example.github_users.data.UserRepository
import com.example.github_users.data.UserRepositoryImpl
import com.example.github_users.domain.usecase.GetUserDetailDataUseCase
import com.example.github_users.domain.usecase.GetUserPagingDataUseCase
import com.example.github_users.ui.viewModel.UserDetailViewModel
import com.example.github_users.ui.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GitHubService> { GitHubRetrofitInstance.api }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { GetUserPagingDataUseCase(get()) }
    single {GetUserDetailDataUseCase(get())}
    viewModel { UserViewModel(get()) }
    viewModel{ UserDetailViewModel(get())}
}