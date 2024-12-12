package com.example.base.utils.okhttp.manager

object HttpConfig {
    var headers = mutableMapOf<String, String>()
    var params = mutableMapOf<String, Any>()

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