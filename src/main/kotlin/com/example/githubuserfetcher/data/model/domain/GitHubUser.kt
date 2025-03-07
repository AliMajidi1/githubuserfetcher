package com.example.githubuserfetcher.data.model.domain

data class GitHubUser(
    val username: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val repos: List<String>
) {
    override fun toString(): String {
        return """
            ┌ Username: $username
            ├ Followers: $followers
            ├ Following: $following
            ├ Created: $createdAt
            └ Repositories: ${repos.take(5).joinToString()}${if (repos.size > 5) "..." else ""}
        """.trimIndent()
    }
}