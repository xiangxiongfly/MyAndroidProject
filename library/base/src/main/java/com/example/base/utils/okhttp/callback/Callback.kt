package com.example.base.utils.okhttp.callback

import com.example.base.utils.okhttp.exception.HttpException

interface Callback<T> {
    fun onStart(){}
    fun onSuccess(result: T)
    fun onError(e: HttpException)
    fun onComplete(){}
}