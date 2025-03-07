package com.example.githubuserfetcher.data.api

import com.example.githubuserfetcher.data.model.response.GitHubUserResponse
import com.example.githubuserfetcher.data.model.response.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<GitHubUserResponse>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<List<RepoResponse>>
}