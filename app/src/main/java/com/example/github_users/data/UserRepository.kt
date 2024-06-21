package com.example.github_users.data

import androidx.paging.Pager
import com.example.github_users.domain.model.User
import com.example.github_users.domain.model.UserDetail

interface UserRepository {
    fun getUserPagingData(): Pager<Int, User>
    suspend fun getUserDetail(userLogin:String):UserDetail
}