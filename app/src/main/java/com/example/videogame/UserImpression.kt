package com.example.videogame

abstract class UserImpression(
    open val userName: String,
    open val timestamp: Long
){
    constructor(): this("", 0)
}