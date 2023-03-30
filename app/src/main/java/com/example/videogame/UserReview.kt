package com.example.videogame

data class UserReview(
    override val userName: String,
    override val timestamp: Long,
    val review: String
): UserImpression(userName,timestamp)
