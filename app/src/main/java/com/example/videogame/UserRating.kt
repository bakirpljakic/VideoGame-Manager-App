package com.example.videogame

data class UserRating(
    override val userName: String,
    override val timestamp: Long,
    val rating: Double
):UserImpression()
