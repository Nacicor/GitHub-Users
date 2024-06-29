package com.example.github_users.domain.usecase

import com.example.github_users.data.UserRepository
import com.example.github_users.domain.model.UserDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailDataUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(userLogin: String): Result<UserDetail> {
        return try {
            val detail = repository.getUserDetail(userLogin)
            Result.success(detail)
        } catch (e: HttpException) {
            when (e.code()) {
                403 -> Result.failure(Exception("Forbidden"))
                401 -> Result.failure(Exception("Requires authentication"))
                304 -> Result.failure(Exception("Not modified"))
                else -> Result.failure(Exception("Unexpected error code(${e.code()})"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(Exception("An unexpected error occurred: ${e.message}"))
        }
    }
}