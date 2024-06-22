package com.example.github_users.utils

import com.example.github_users.domain.model.UserDetail

sealed class UserDetailState {
    data object Loading : UserDetailState()
    data class Success(val userDetail: UserDetail) : UserDetailState()
    data class Error(val message: String) : UserDetailState()
}