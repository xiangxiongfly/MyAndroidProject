package com.example.tools.okhttp.bean

data class User(
    val admin: Boolean,
    val coinCount: Int,
    val id: Int,
    val nickname: String,
    val publicName: String,
    val username: String
)