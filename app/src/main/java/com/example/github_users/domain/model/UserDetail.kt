package com.example.github_users.domain.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
    val name: String? = "empty user",
    val bio: String? = "empty",
    val login: String = "empty",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    val location: String? = "",
    val blog: String? = "empty",
    val id: Int = 0
)