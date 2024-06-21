package com.example.github_users.domain.usecase

import com.example.github_users.data.UserRepository
import com.example.github_users.domain.model.UserDetail

class GetUserDetailDataUseCase(private val repository: UserRepository) {
    suspend fun execute(userLogin:String):UserDetail{
        return repository.getUserDetail(userLogin)
    }

}