package com.example.tools.okhttp.bean

data class BaseBean<T>(val errorCode: Int, val errorMsg: String, val data: T?)