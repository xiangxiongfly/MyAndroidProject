package com.xiangxiongfly.androidtools.okhttp.bean

data class User(
    val admin: Boolean,
    val coinCount: Int,
    val id: Int,
    val nickname: String,
    val publicName: String,
    val username: String
)