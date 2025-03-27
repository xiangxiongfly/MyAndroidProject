package com.xiangxiongfly.androidtools.okhttp.bean

data class BeanFactory<T>(val errorCode: Int, val errorMsg: String, val data: T?)