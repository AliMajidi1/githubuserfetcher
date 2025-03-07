package com.example.githubuserfetcher

import com.example.githubuserfetcher.data.cache.UserCache
import com.example.githubuserfetcher.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

fun main() = runBlocking {
    val repository = UserRepository()

    while (true) {
        printMenu()
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> fetchUser(repository)
            2 -> displayAllUsers()
            3 -> searchByUsername()
            4 -> searchByRepoName()
            5 -> updateUsersInCache()
            6 -> exitProcess(0)
            else -> println("Invalid option")
        }
    }
}

private fun printMenu() {
    println("""
        1- Fetch user by username
        2- List all cached users
        3- Search by username in cache
        4- Search by repository name in cache
        5- Update all users in cache
        6- Exit
    """.trimIndent())
}

private suspend fun fetchUser(repository: UserRepository) {
    print("Enter username: ")
    readlnOrNull()?.takeIf { it.isNotBlank() }?.let { username ->
        try {
            UserCache.getUser(username)?.let {
                println("User '${it.username}' fetched from cache!")
                println(it)
            }?: run {
                repository.fetchUser(username)?.let {
                    println("User '${it.username}' fetched successfully!")
                    println(it)
                }
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    } ?: println("Invalid username")
}

private fun displayAllUsers() {
    UserCache.getAllUsers().takeIf { it.isNotEmpty() }?.forEach {
        println(it)
        println("──────────────────────────────────────────────")
    } ?: println("No users in cache")
}

private fun searchByUsername() {
    print("Search username: ")
    readlnOrNull()?.let { query ->
        UserCache.searchByUsername(query).takeIf { it.isNotEmpty() }?.forEach {
            println("Found user: ${it.username}")
            println(it)
            println("──────────────────────────────────────────────")
        } ?: println("No matching users found")
    }
}

private fun searchByRepoName() {
    print("Search repository: ")
    readlnOrNull()?.let { query ->
        UserCache.searchByRepoName(query).takeIf { it.isNotEmpty() }?.forEach { user ->
            println("User: ${user.username}")
            user.repos.filter { it.contains(query, true) }.forEach { println("   - $it") }
            println("──────────────────────────────────────────────")
        } ?: println("No matching repositories found")
    }
}

private suspend fun updateUsersInCache() {
    UserCache.getAllUsers().forEach { user ->
        try {
            UserRepository().fetchUser(user.username)
            println("User '${user.username}' updated successfully!")
        } catch (e: Exception) {
            println("Error updating user '${user.username}': ${e.message}")
        }
    }
}
