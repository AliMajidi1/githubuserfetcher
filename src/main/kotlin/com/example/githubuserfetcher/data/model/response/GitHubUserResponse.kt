package com.example.githubuserfetcher.data.model.response

import com.google.gson.annotations.SerializedName

data class GitHubUserResponse(
    val login: String,
    val followers: Int,
    val following: Int,
    @SerializedName("created_at") val createdAt: String
)