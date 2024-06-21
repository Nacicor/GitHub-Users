package com.example.github_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.github_users.ui.screen.UserListScreen
import com.example.github_users.ui.theme.GithubusersTheme
import com.example.github_users.ui.viewModel.UserDetailViewModel
import com.example.github_users.ui.viewModel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModel()
    private val userDetailViewModel:UserDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubusersTheme {
                UserListScreen(userViewModel = userViewModel , userDetailViewModel = userDetailViewModel)
            }
        }
    }
}
