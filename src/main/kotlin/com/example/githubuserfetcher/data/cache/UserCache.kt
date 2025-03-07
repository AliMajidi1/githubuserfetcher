package com.example.githubuserfetcher.data.cache

import com.example.githubuserfetcher.data.model.domain.GitHubUser

object UserCache {
    private val users = mutableMapOf<String, GitHubUser>()

    fun getUser(username: String): GitHubUser? = users[username.lowercase()]
    fun addUser(user: GitHubUser) { users[user.username.lowercase()] = user }
    fun getAllUsers(): List<GitHubUser> = users.values.toList()
    fun searchByUsername(query: String): List<GitHubUser> =
        users.values.filter { it.username.contains(query, true) }
    fun searchByRepoName(query: String): List<GitHubUser> =
        users.values.filter { user -> user.repos.any { it.contains(query, true) } }
}