package com.xiangxiongfly.common.utils.okhttp.manager

import com.hjq.gson.factory.GsonFactory


class HttpHeaders(
    private val headers: MutableMap<String, String> = mutableMapOf()
) {

    init {
        headers.putAll(HttpConfig.getHeaders())
    }

    // 添加
    fun addHeader(key: String, value: String) {
        headers.put(key, value)
    }

    // 删除
    fun remove(key: String) {
        headers.remove(key)
    }

    // 获取所有参数
    fun getHeaders(): MutableMap<String, String> {
        return headers // 返回不可变列表
    }

    // 清空
    fun clear() {
        headers.clear()
    }

    // 转为JSON字符串
    fun toJson(): String {
        return GsonFactory.getSingletonGson().toJson(headers)
    }

    // 检查是否有参数
    fun isEmpty(): Boolean {
        return headers.isEmpty()
    }
}