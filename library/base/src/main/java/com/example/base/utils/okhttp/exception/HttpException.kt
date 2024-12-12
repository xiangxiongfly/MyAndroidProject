package com.example.base.utils.okhttp.exception

/**
 * 网络请求异常
 */
data class HttpException(val code: Int, val msg: String?, val ex: Throwable? = null) :
    Exception(msg, ex)