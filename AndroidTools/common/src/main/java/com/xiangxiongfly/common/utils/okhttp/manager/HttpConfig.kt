package com.xiangxiongfly.common.utils.okhttp.manager

object HttpConfig {
    private val headers = mutableMapOf<String, String>()
    private val params = mutableMapOf<String, Any>()

    fun getHeaders() = headers

    fun getParams() = params

    fun addHeader(key: String, value: String) {
        headers.put(key, value)
    }

    fun removeHeader(key: String) {
        headers.remove(key)
    }

    fun clearHeaders() {
        headers.clear()
    }

    fun addParams(key: String, value: Any) {
        params.put(key, value)
    }

    fun removeParam(key: String) {
        params.remove(key)
    }

    fun clearParams() {
        params.clear()
    }
}