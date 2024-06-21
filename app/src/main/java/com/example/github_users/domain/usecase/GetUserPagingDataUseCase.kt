package com.example.github_users.domain.usecase

import androidx.paging.PagingData
import com.example.github_users.data.UserRepository
import com.example.github_users.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUserPagingDataUseCase(private val userRepository: UserRepository) {
    fun execute(): Flow<PagingData<User>> {
        return userRepository.getUserPagingData().flow
    }
}