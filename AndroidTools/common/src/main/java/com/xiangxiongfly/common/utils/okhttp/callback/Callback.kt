package com.xiangxiongfly.common.utils.okhttp.callback

import com.xiangxiongfly.common.utils.okhttp.exception.HttpException

interface Callback<T> {
    fun onStart() {}
    fun onSuccess(result: T)
    fun onError(e: HttpException)
    fun onComplete() {}
}