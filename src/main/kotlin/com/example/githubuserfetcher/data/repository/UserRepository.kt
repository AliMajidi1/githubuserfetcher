package com.example.githubuserfetcher.data.repository

import com.example.githubuserfetcher.data.api.GitHubApiService
import com.example.githubuserfetcher.data.api.RetrofitClient
import com.example.githubuserfetcher.data.cache.UserCache
import com.example.githubuserfetcher.data.model.domain.GitHubUser
import java.io.IOException

class UserRepository(private val apiService: GitHubApiService = RetrofitClient.instance) {

    suspend fun fetchUser(username: String): GitHubUser? {
        UserCache.getUser(username)?.let { return it }

        return try {
            val userResponse = apiService.getUser(username)
            if (!userResponse.isSuccessful) handleError(userResponse.code(), "User")
            val userDto = userResponse.body()!!

            val reposResponse = apiService.getUserRepos(username)
            if (!reposResponse.isSuccessful) handleError(reposResponse.code(), "Repositories")
            val repos = reposResponse.body()!!.map { it.name }

            GitHubUser(
                username = userDto.login,
                followers = userDto.followers,
                following = userDto.following,
                createdAt = userDto.createdAt,
                repos = repos
            ).also { UserCache.addUser(it) }
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }

    private fun handleError(code: Int, entity: String): Nothing {
        throw when (code) {
            404 -> Exception("$entity not found")
            403 -> Exception("API rate limit exceeded")
            else -> Exception("API error: $code")
        }
    }
}