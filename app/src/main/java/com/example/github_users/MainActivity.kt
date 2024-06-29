package com.example.github_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.github_users.ui.screen.UserListScreen
import com.example.github_users.ui.theme.GithubusersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubusersTheme {
                UserListScreen()
            }
        }
    }
}
