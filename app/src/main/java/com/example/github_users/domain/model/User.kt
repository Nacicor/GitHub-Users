package com.example.github_users.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
)