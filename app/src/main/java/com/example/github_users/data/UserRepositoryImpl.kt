package com.example.github_users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.github_users.domain.model.User
import com.example.github_users.domain.model.UserDetail

class UserRepositoryImpl(private val service: GitHubService): UserRepository {
    override fun getUserPagingData(): Pager<Int, User> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserPagingSource(service) }
        )
    }

    override suspend fun getUserDetail(userLogin: String): UserDetail {
        return service.getUserDetail(userLogin)
    }
}