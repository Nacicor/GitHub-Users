package com.example.github_users.data

import com.example.github_users.domain.model.User
import com.example.github_users.domain.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") perPage: Int): List<User>
    @GET("users/{userLogin}")
    suspend fun getUserDetail(@Path("userLogin") userLogin:String):UserDetail
}