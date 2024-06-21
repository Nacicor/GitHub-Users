package com.example.github_users.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.github_users.domain.model.User

class UserPagingSource(private val service: GitHubService) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val since = params.key ?: 0
            val response = service.getUsers(since, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (since == 0) null else since - 1,
                nextKey = response.lastOrNull()?.id
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}